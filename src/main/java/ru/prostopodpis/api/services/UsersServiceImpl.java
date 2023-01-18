package ru.prostopodpis.api.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.prostopodpis.api.dtos.accounts.AccountUpdateRequest;
import ru.prostopodpis.api.entity.Tariff;
import ru.prostopodpis.api.entity.User;
import ru.prostopodpis.api.repositories.ReactiveUserRepository;

import java.util.List;
import java.util.Random;
import java.util.UUID;


/**
 * author: Sergey Khaylov
 * email: <a href="mailto:info@proextlab.ru">info@proextlab.ru</a>
 * org: ProextLab
 * site: <a href="https://proextlab.ru">ProextLab</a>
 * created: 11.09.2022
 **/

@Service
@RequiredArgsConstructor
@Log4j2
public class UsersServiceImpl implements UsersService{

    @Value("${new-user-balance}")
    private Float NEW_USER_BALANCE;

    private final PasswordEncoder passwordEncoder;

    private final ReactiveUserRepository repository;
    private final TariffHistoryService tariffHistoryService;

    private final SmtpBZSender emailSender;

    @Value("${default-tariff}")
    private String defaultTariffId;

    @Override
    public Mono<User> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Mono<User> findByEmailOrPhone(String emailOrPhone) {
        return repository.findByEmailOrPhone(emailOrPhone);
    }

    @Override
    public Mono<User> findByEmailOrPhone(String email, String phone) {
        return repository.findByEmailOrPhone(email, phone);
    }

    @Override
    public Mono<User> create(String password, String email, String phone) {
        User user = new User();
        user.setEmail(email);
        user.setPhone(phone);
        user.setConfirmCode(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(password));
        user.setRoles(List.of("USER"));
        user.setCurrentTariff(defaultTariffId);
        user.setBalance(NEW_USER_BALANCE);

        emailSender.send(user.getEmail(),
                "Подтверждение регистрации",
                String.format("""
        <p>Вы зарегистрировались на портале <a href='https://prostopodpis.ru'>prostopodpis.ru</a></p>
        <p>Для подтверждения регистрации перейдите <a href="https://lk.prostopodpis.ru/auth/confirm/%s">по ссылке</a><p>
        """, user.getConfirmCode())
        );

        return repository.save(user)
                .map(user1 -> {
                    tariffHistoryService.changeTariff(user1.getId(), defaultTariffId).subscribe();
                    return user1;
                });
    }

    @Override
    public Mono<Void> restorePassword(String emailOrPhone) {
        return findByEmailOrPhone(emailOrPhone, emailOrPhone)
                .flatMap(user -> {
                    Random random = new Random();

                    String newPassword = Integer.toString(random.nextInt(99999));
                    String secretPassword = passwordEncoder.encode(newPassword);
                    user.setPassword(secretPassword);

                    emailSender.send(user.getEmail(),
                            "Восстановление пароля",
                            String.format("<p>Ваш новый пароль: %s</p><p>Используйте его для входа в <a href='https://lk.prostopodpis.ru/auth/login'>личный кабинет</a>", newPassword)
                    );
                    return repository.save(user);
                })
                .then();
    }

    @Override
    public Mono<Void> confirmRegistration(String code) {
        return repository.findByConfirmCode(code)
                .flatMap(user -> {
                    user.setConfirmCode(null);
                    user.setEnabled(true);
                    return repository.save(user);
                })
                .then();
    }

    @Override
    public Mono<User> updateAccount(User user, AccountUpdateRequest accountUpdateRequest) {
        user.setOrgName(accountUpdateRequest.getOrgName());
        user.setTimezone(accountUpdateRequest.getTimezone());
        user.setDefaultSender(accountUpdateRequest.getDefaultSender());

        if (!user.getEmail().equals(accountUpdateRequest.getEmail())) {
            findByEmailOrPhone(accountUpdateRequest.getEmail())
                    .switchIfEmpty(setUserEmail(user, accountUpdateRequest.getEmail()))
                    .subscribe();
        }

        if (!user.getPhone().equals(accountUpdateRequest.getPhone())) {
            findByEmailOrPhone(accountUpdateRequest.getPhone())
                    .switchIfEmpty(setUserPhone(user, accountUpdateRequest.getPhone()))
                    .subscribe();
        }

        if (accountUpdateRequest.getPassword() != null && !accountUpdateRequest.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(accountUpdateRequest.getPassword()));
        }

        return repository.save(user);

    }

    private Mono<User> setUserEmail(User user, String email) {
        user.setEmail(email);
        return Mono.just(user);
    }

    private Mono<User> setUserPhone(User user, String phone) {
        user.setPhone(phone);
        return Mono.just(user);
    }

    @Override
    public Mono<User> activateTariff(User user, String tariffId) {
        user.setCurrentTariff(tariffId);
        return repository.save(user)
                .map(user1 -> {
                    tariffHistoryService.changeTariff(user1.getId(), tariffId).subscribe();
                    return user1;
                });
    }

    private Mono<User> changeBalance(User user, float value) {
        if (user.getBalance() == null) {
            user.setBalance(0f);
        }
        user.setBalance(user.getBalance() + value);
        return repository.save(user);
    }

    @Override
    public Mono<User> increaseBalance(User user, Float value) {
        return changeBalance(user, value);
    }

    @Override
    public Mono<User> decreaseBalance(User user, Float value) {
        return changeBalance(user, -1 * value);
    }

    @Override
    public Flux<User> findAll() {
        return repository.findAll();
    }

    @Override
    public Mono<Boolean> canActivateTariff(User user, Tariff tariff) {
        return Mono.just(user.getBalance() >= tariff.getMonthCost());
    }
}
