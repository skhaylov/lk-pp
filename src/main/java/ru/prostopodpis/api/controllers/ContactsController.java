package ru.prostopodpis.api.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.prostopodpis.api.dtos.project_recipients.CreateProjectRecipientRequest;
import ru.prostopodpis.api.dtos.project_recipients.ProjectRecipientDto;
import ru.prostopodpis.api.dtos.project_recipients.UpdateProjectRecipientRequest;
import ru.prostopodpis.api.services.ProjectRecipientService;
import ru.prostopodpis.api.services.ProjectsService;
import ru.prostopodpis.api.services.SendersService;
import ru.prostopodpis.api.services.UsersService;

import javax.validation.Valid;
import java.security.Principal;

/**
 * author: Sergey Khaylov
 * email: <a href="mailto:info@proextlab.ru">info@proextlab.ru</a>
 * org: ProextLab
 * site: <a href="https://proextlab.ru">ProextLab</a>
 * created: 18.09.2022
 **/

@RestController
@RequestMapping("/api/v1/contacts")
@RequiredArgsConstructor
public class ContactsController {
    private final ProjectRecipientService projectRecipientService;
    private final UsersService usersService;
    private final ProjectsService projectsService;
    private final SendersService sendersService;

    @GetMapping
    public Flux<ProjectRecipientDto> findAllByUser(Mono<Principal> principalMono) {
        return principalMono
                .flatMap(principal -> usersService.findByEmailOrPhone(principal.getName()))
                .flatMapMany(projectsService::findAllByUser)
                .flatMap(project -> sendersService
                                .findById(project.getSender())
                                .flatMapMany(sender -> projectRecipientService.findByProject(project)
                                    .flatMap(projectRecipient -> Mono.just(ProjectRecipientDto.of(projectRecipient, sender)))
                                )
                );
    }

    @GetMapping("/{id}")
    public Mono<ProjectRecipientDto> findAllByUserAndId(Mono<Principal> principalMono,
                                                        @PathVariable String id) {

        return principalMono
                .flatMap(principal -> usersService.findByEmailOrPhone(principal.getName()))
                .flatMapMany(projectsService::findAllByUser)
                .flatMap(project ->
                        sendersService.findById(project.getSender())
                                .flatMapMany(sender -> projectRecipientService
                                        .findByProjectAndId(project, id)
                                        .flatMapMany(projectRecipient -> Mono.just(ProjectRecipientDto.of(projectRecipient, sender))))
                )
                .singleOrEmpty();
    }

    @PutMapping("/{id}")
    public Mono<ProjectRecipientDto> updateProjectRecipient(Mono<Principal> principalMono,
                                                            @PathVariable String id,
                                                            @Valid @RequestBody UpdateProjectRecipientRequest request) {
        return principalMono
                .flatMap(principal -> usersService.findByEmailOrPhone(principal.getName()))
                .flatMap(user -> projectRecipientService.findById(id))
                .flatMap(projectRecipient -> projectRecipientService.update(projectRecipient, request))
                .map(ProjectRecipientDto::of);
    }

    @PostMapping
    public Mono<ProjectRecipientDto> create(Mono<Principal> principalMono,
                                            @Valid @RequestBody CreateProjectRecipientRequest request) {
        return principalMono
                .flatMap(principal -> usersService.findByEmailOrPhone(principal.getName()))
                .flatMap(user -> projectsService.findByUserAndId(user, request.getProjectId()))
                .flatMap(project -> projectRecipientService.create(project, request))
                .map(ProjectRecipientDto::of);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteById(Mono<Principal> principalMono,
                                 @PathVariable String id) {
        return principalMono
                .flatMap(principal -> usersService.findByEmailOrPhone(principal.getName()))
                .flatMapMany(projectsService::findAllByUser)
                .flatMap(project -> projectRecipientService.findByProjectAndId(project, id))
                .singleOrEmpty()
                .flatMap(projectRecipientService::delete)
                .then();
    }
}
