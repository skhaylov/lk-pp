package ru.prostopodpis.api.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.prostopodpis.api.entity.Schet;

/**
 * author: Sergey Khaylov
 * email: <a href="mailto:info@proextlab.ru">info@proextlab.ru</a>
 * org: ProextLab
 * site: <a href="https://proextlab.ru">ProextLab</a>
 * created: 06.10.2022
 **/

public interface ReactiveSchetRepository extends ReactiveMongoRepository<Schet, String> {
    Flux<Schet> findByUserId(String userId);
    Mono<Schet> findByUserIdAndId(String userId, String id);
}
