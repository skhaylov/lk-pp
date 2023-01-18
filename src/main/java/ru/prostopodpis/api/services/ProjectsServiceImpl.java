package ru.prostopodpis.api.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.prostopodpis.api.dtos.projects.CreateProjectRequest;
import ru.prostopodpis.api.dtos.projects.UpdateProjectRequest;
import ru.prostopodpis.api.entity.Project;
import ru.prostopodpis.api.entity.User;
import ru.prostopodpis.api.repositories.ReactiveProjectsRepository;

/**
 * author: Sergey Khaylov
 * email: <a href="mailto:info@proextlab.ru">info@proextlab.ru</a>
 * org: ProextLab
 * site: <a href="https://proextlab.ru">ProextLab</a>
 * created: 12.09.2022
 **/

@Service
@RequiredArgsConstructor
public class ProjectsServiceImpl implements ProjectsService {
    private final ReactiveProjectsRepository repository;

    @Override
    public Mono<Project> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Flux<Project> findAllByUser(User user) {
        return repository.findByUserIdOrderByCreatedDate(user.getId());
    }

    @Override
    public Mono<Boolean> existsByApiKey(String apiKey) {
        return repository.existsByApiKey(apiKey);
    }

    @Override
    public Mono<Project> create(User user, CreateProjectRequest createProjectRequest) {
        Project project = new Project();
        project.setName(createProjectRequest.getName());
        project.setDescription(createProjectRequest.getDescription());
        project.setApiKey(createProjectRequest.getApiKey());
        project.setUserId(user.getId());
        project.setCallbackUrl(createProjectRequest.getCallbackUrl());
        project.setSender(createProjectRequest.getSender());
        return repository.save(project);
    }

    @Override
    public Mono<Project> update(Project project, UpdateProjectRequest updateProjectRequest) {
        project.setName(updateProjectRequest.getName());
        project.setDescription(updateProjectRequest.getDescription());
        project.setApiKey(updateProjectRequest.getApiKey());
        project.setSender(updateProjectRequest.getSender());
        project.setCallbackUrl(updateProjectRequest.getCallbackUrl());
        return repository.save(project);
    }

    @Override
    public Mono<Project> findByUserAndId(User user, String id) {
        return repository.findByUserIdAndId(user.getId(), id);
    }

    @Override
    public Mono<Void> deleteByUserAndId(User user, String id) {
        return repository.deleteByUserIdAndId(user.getId(), id);
    }

    @Override
    public Mono<Project> findByUserAndApiKey(User user, String apiKey) {
        return repository.findByUserIdAndApiKey(user.getId(), apiKey);
    }

    @Override
    public Mono<Project> findByApiKey(String apiKey) {
        return repository.findByApiKey(apiKey);
    }

    @Override
    public Flux<Project> findAll() {
        return repository.findAll();
    }
}
