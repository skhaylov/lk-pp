package ru.prostopodpis.api.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;
import ru.prostopodpis.api.entity.Requsite;

/**
 * author: Sergey Khaylov
 * email: <a href="mailto:info@proextlab.ru">info@proextlab.ru</a>
 * org: ProextLab
 * site: <a href="https://proextlab.ru">ProextLab</a>
 * created: 11.09.2022
 **/

public interface ReactiveRequsiteRepository extends ReactiveMongoRepository<Requsite, String> {
    Mono<Requsite> findByUserId(String userId);
}
