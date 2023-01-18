package ru.prostopodpis.api.services;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.prostopodpis.api.entity.Sender;
import ru.prostopodpis.api.entity.User;

/**
 * author: Sergey Khaylov
 * email: <a href="mailto:info@proextlab.ru">info@proextlab.ru</a>
 * org: ProextLab
 * site: <a href="https://proextlab.ru">ProextLab</a>
 * created: 13.09.2022
 **/

public interface SendersService {
    Flux<Sender> findAllByUser(User user);
    Flux<Sender> findAllByUserWithDefault(User user);

    Mono<Sender> create(User user, String name);

    Mono<Sender> findByUserAndId(User user, String id);

    Mono<Sender> update(User user, String id, String name);

    Mono<Void> delete(User user, String id);

    Mono<Sender> findById(String id);
}
