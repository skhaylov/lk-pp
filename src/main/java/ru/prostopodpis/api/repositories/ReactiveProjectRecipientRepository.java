package ru.prostopodpis.api.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.prostopodpis.api.entity.ProjectRecipient;

/**
 * author: Sergey Khaylov
 * email: <a href="mailto:info@proextlab.ru">info@proextlab.ru</a>
 * org: ProextLab
 * site: <a href="https://proextlab.ru">ProextLab</a>
 * created: 17.09.2022
 **/

public interface ReactiveProjectRecipientRepository extends ReactiveMongoRepository<ProjectRecipient, String > {
    Flux<ProjectRecipient> findByProjectId(String projectId);
    Mono<ProjectRecipient> findByProjectIdAndId(String projectId, String id);
}
