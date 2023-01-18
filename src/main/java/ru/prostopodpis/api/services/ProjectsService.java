package ru.prostopodpis.api.services;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.prostopodpis.api.dtos.projects.CreateProjectRequest;
import ru.prostopodpis.api.dtos.projects.UpdateProjectRequest;
import ru.prostopodpis.api.entity.Project;
import ru.prostopodpis.api.entity.User;

/**
 * author: Sergey Khaylov
 * email: <a href="mailto:info@proextlab.ru">info@proextlab.ru</a>
 * org: ProextLab
 * site: <a href="https://proextlab.ru">ProextLab</a>
 * created: 12.09.2022
 **/

public interface ProjectsService {
    Flux<Project> findAllByUser(User user);

    Mono<Boolean> existsByApiKey(String apiKey);

    Mono<Project> create(User user, CreateProjectRequest createProjectRequestMono);

    Mono<Project> findByUserAndId(User user, String id);
    Mono<Project> findByUserAndApiKey(User user, String apiKey);
    Mono<Project> findById(String id);
    Mono<Project> findByApiKey(String apiKey);

    Mono<Project> update(Project project, UpdateProjectRequest updateProjectRequest);

    Mono<Void> deleteByUserAndId(User user, String id);

    Flux<Project> findAll();
}
