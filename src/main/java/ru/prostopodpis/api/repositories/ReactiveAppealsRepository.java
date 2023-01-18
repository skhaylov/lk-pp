package ru.prostopodpis.api.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.prostopodpis.api.entity.Appeal;

/**
 * author: Sergey Khaylov
 * email: <a href="mailto:info@proextlab.ru">info@proextlab.ru</a>
 * org: ProextLab
 * site: <a href="https://proextlab.ru">ProextLab</a>
 * created: 05.10.2022
 **/

public interface ReactiveAppealsRepository extends ReactiveMongoRepository<Appeal, String> {
    Flux<Appeal> findByUserId(String userId);
    Mono<Appeal> findByUserIdAndId(String userId, String id);
}
