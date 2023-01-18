package ru.prostopodpis.api.services;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.prostopodpis.api.dtos.accounts.AccountUpdateRequest;
import ru.prostopodpis.api.dtos.admin.users.AdminUserResponseDto;
import ru.prostopodpis.api.entity.Tariff;
import ru.prostopodpis.api.entity.User;

/**
 * author: Sergey Khaylov
 * email: <a href="mailto:info@proextlab.ru">info@proextlab.ru</a>
 * org: ProextLab
 * site: <a href="https://proextlab.ru">ProextLab</a>
 * created: 11.09.2022
 **/

public interface UsersService {
    Mono<User> findById(String id);
    Mono<User> findByEmailOrPhone(String emailOrPhone);
    Mono<User> findByEmailOrPhone(String email, String phone);
    Mono<User> create(String password, String email, String phone);

    Mono<Void> restorePassword(String emailOrPhone);

    Mono<Void> confirmRegistration(String code);

    Mono<User> updateAccount(User user, AccountUpdateRequest accountUpdateRequest);

    Mono<User> activateTariff(User user, String tariffId);

    Mono<User> increaseBalance(User user, Float value);
    Mono<User> decreaseBalance(User user, Float value);

    Flux<User> findAll();

    Mono<Boolean> canActivateTariff(User user, Tariff tariff);
}
