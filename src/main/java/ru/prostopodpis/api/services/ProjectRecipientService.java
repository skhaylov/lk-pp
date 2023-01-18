package ru.prostopodpis.api.services;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.prostopodpis.api.dtos.project_recipients.CreateProjectRecipientRequest;
import ru.prostopodpis.api.dtos.project_recipients.ProjectRecipientDto;
import ru.prostopodpis.api.dtos.project_recipients.UpdateProjectRecipientRequest;
import ru.prostopodpis.api.entity.Project;
import ru.prostopodpis.api.entity.ProjectRecipient;

/**
 * author: Sergey Khaylov
 * email: <a href="mailto:info@proextlab.ru">info@proextlab.ru</a>
 * org: ProextLab
 * site: <a href="https://proextlab.ru">ProextLab</a>
 * created: 17.09.2022
 **/

public interface ProjectRecipientService {
    Mono<ProjectRecipient> create(Project project, CreateProjectRecipientRequest createRequest);
    Flux<ProjectRecipient> findByProject(Project project);

    Mono<ProjectRecipient> findById(String id);

    Mono<ProjectRecipient> findByProjectAndId(Project project, String id);

    Mono<ProjectRecipient> update(ProjectRecipient projectRecipient, UpdateProjectRecipientRequest request);

    Mono<Void> delete(ProjectRecipient projectRecipient);

    Mono<String> generateJoinUrl(ProjectRecipient projectRecipient);
}
