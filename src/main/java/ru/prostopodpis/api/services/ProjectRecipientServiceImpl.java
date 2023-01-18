package ru.prostopodpis.api.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.prostopodpis.api.dtos.project_recipients.CreateProjectRecipientRequest;
import ru.prostopodpis.api.dtos.project_recipients.UpdateProjectRecipientRequest;
import ru.prostopodpis.api.entity.Project;
import ru.prostopodpis.api.entity.ProjectRecipient;
import ru.prostopodpis.api.repositories.ReactiveProjectRecipientRepository;

/**
 * author: Sergey Khaylov
 * email: <a href="mailto:info@proextlab.ru">info@proextlab.ru</a>
 * org: ProextLab
 * site: <a href="https://proextlab.ru">ProextLab</a>
 * created: 17.09.2022
 **/

@Service
@RequiredArgsConstructor
public class ProjectRecipientServiceImpl implements ProjectRecipientService {
    private final ReactiveProjectRecipientRepository repository;
    private final SendersService sendersService;
    private final ProjectsService projectsService;

    @Override
    public Mono<ProjectRecipient> create(Project project, CreateProjectRecipientRequest createRequest) {
        ProjectRecipient projectRecipient = new ProjectRecipient();
        projectRecipient.setProjectId(project.getId());
        projectRecipient.setName(createRequest.getName());
        projectRecipient.setEmail(createRequest.getEmail());
        projectRecipient.setPhone(createRequest.getPhone());
        return repository.save(projectRecipient);
    }

    @Override
    public Flux<ProjectRecipient> findByProject(Project project) {
        return findByProject(project.getId());
    }

    public Flux<ProjectRecipient> findByProject(String projectId) {
        return repository.findByProjectId(projectId);
    }

    @Override
    public Mono<ProjectRecipient> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Mono<ProjectRecipient> findByProjectAndId(Project project, String id) {
        return repository.findByProjectIdAndId(project.getId(), id);
    }

    @Override
    public Mono<ProjectRecipient> update(ProjectRecipient projectRecipient, UpdateProjectRecipientRequest request) {
        projectRecipient.setName(request.getName());
        projectRecipient.setPhone(request.getPhone());
        projectRecipient.setEmail(request.getEmail());
        return repository.save(projectRecipient);
    }

    @Override
    public Mono<Void> delete(ProjectRecipient projectRecipient) {
        return repository.delete(projectRecipient).then();
    }

    @Override
    public Mono<String> generateJoinUrl(ProjectRecipient projectRecipient) {
        return projectsService.findById(projectRecipient.getProjectId())
                        .flatMap(project -> sendersService.findById(project.getSender()))
                                .flatMap(sender -> Mono.just(
                                        String.format("https://t.me/%s?start=%s", sender.getTelegramBotName(), projectRecipient.getId())
                                ));
    }
}
