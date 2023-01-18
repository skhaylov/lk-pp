package ru.prostopodpis.api.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.prostopodpis.api.entity.Dogovor;

/**
 * author: Sergey Khaylov
 * email: <a href="mailto:info@proextlab.ru">info@proextlab.ru</a>
 * org: ProextLab
 * site: <a href="https://proextlab.ru">ProextLab</a>
 * created: 04.10.2022
 **/

public interface ReactiveDogovorRepository extends ReactiveMongoRepository<Dogovor, String> {
    Flux<Dogovor> findByUserId(String userId);
    Mono<Dogovor> findByUserIdAndId(String userId, String id);
}
