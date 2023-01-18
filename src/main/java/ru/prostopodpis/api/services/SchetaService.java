package ru.prostopodpis.api.services;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.prostopodpis.api.entity.Schet;
import ru.prostopodpis.api.entity.User;

/**
 * author: Sergey Khaylov
 * email: <a href="mailto:info@proextlab.ru">info@proextlab.ru</a>
 * org: ProextLab
 * site: <a href="https://proextlab.ru">ProextLab</a>
 * created: 06.10.2022
 **/

public interface SchetaService {
    Flux<Schet> findByUser(User user);
    Mono<Schet> findByUserAndId(User user, String id);

    Mono<Schet> create(User user, Float amount);
}
