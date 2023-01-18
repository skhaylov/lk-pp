package ru.prostopodpis.api.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.prostopodpis.api.entity.Schet;
import ru.prostopodpis.api.entity.User;
import ru.prostopodpis.api.enums.SchetStatusEnum;
import ru.prostopodpis.api.repositories.ReactiveSchetRepository;

/**
 * author: Sergey Khaylov
 * email: <a href="mailto:info@proextlab.ru">info@proextlab.ru</a>
 * org: ProextLab
 * site: <a href="https://proextlab.ru">ProextLab</a>
 * created: 06.10.2022
 **/

@Service
@RequiredArgsConstructor
public class SchetaServiceImpl implements SchetaService {

    private final ReactiveSchetRepository repository;

    @Override
    public Flux<Schet> findByUser(User user) {
        return repository.findByUserId(user.getId());
    }

    @Override
    public Mono<Schet> findByUserAndId(User user, String id) {
        return repository.findByUserIdAndId(user.getId(), id);
    }

    @Override
    public Mono<Schet> create(User user, Float amount) {
        Schet schet = new Schet();
        schet.setAmount(amount);
        schet.setUserId(user.getId());
        schet.setStatus(SchetStatusEnum.NEW);
        return repository.save(schet);
    }
}
