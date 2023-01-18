package ru.prostopodpis.api.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.prostopodpis.api.entity.Project;

/**
 * author: Sergey Khaylov
 * email: <a href="mailto:info@proextlab.ru">info@proextlab.ru</a>
 * org: ProextLab
 * site: <a href="https://proextlab.ru">ProextLab</a>
 * created: 12.09.2022
 **/

public interface ReactiveProjectsRepository extends ReactiveMongoRepository<Project, String> {
    Flux<Project> findByUserIdOrderByCreatedDate(String userId);

    Mono<Boolean> existsByApiKey(String apiKey);

    Mono<Project> findByApiKey(String apiKey);
    Mono<Project> findByUserIdAndId(String userId, String id);
    Mono<Project> findByUserIdAndApiKey(String userId, String apiKey);
    Mono<Void> deleteByUserIdAndId(String userId, String id);
}
