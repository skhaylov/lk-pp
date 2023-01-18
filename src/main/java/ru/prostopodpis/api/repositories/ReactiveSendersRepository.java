package ru.prostopodpis.api.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.prostopodpis.api.entity.Sender;

/**
 * author: Sergey Khaylov
 * email: <a href="mailto:info@proextlab.ru">info@proextlab.ru</a>
 * org: ProextLab
 * site: <a href="https://proextlab.ru">ProextLab</a>
 * created: 13.09.2022
 **/

public interface ReactiveSendersRepository extends ReactiveMongoRepository<Sender, String> {
    Flux<Sender> findByUserId(String userId);

    Mono<Sender> findByUserIdAndId(String userId, String id);

    Mono<Void> deleteByUserIdAndId(String userId, String id);

    Flux<Sender> findByUserIdIsNull();
}
