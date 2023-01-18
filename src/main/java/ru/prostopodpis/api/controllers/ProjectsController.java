package ru.prostopodpis.api.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.prostopodpis.api.dtos.projects.CreateProjectRequest;
import ru.prostopodpis.api.dtos.projects.ProjectResponse;
import ru.prostopodpis.api.dtos.projects.UpdateProjectRequest;
import ru.prostopodpis.api.entity.User;
import ru.prostopodpis.api.services.ProjectsService;
import ru.prostopodpis.api.services.UsersService;

import javax.validation.Valid;
import java.security.Principal;

/**
 * author: Sergey Khaylov
 * email: <a href="mailto:info@proextlab.ru">info@proextlab.ru</a>
 * org: ProextLab
 * site: <a href="https://proextlab.ru">ProextLab</a>
 * created: 12.09.2022
 **/

@RestController
@RequestMapping("/api/v1/projects")
@RequiredArgsConstructor
public class ProjectsController {
    private final ProjectsService projectsService;
    private final UsersService usersService;

    @GetMapping
    public Flux<ProjectResponse> findAllByUser(Mono<Principal> principalMono) {
        return principalMono
                .flatMap(principal -> usersService.findByEmailOrPhone(principal.getName()))
                .flatMapMany(projectsService::findAllByUser)
                .map(ProjectResponse::of);
    }

    @GetMapping("/{id}")
    public Mono<ProjectResponse> findProjectByUser(Mono<Principal> principalMono,
                                                   @PathVariable String id) {
        return principalMono
                .flatMap(principal -> usersService.findByEmailOrPhone(principal.getName()))
                .flatMap(user -> projectsService.findByUserAndId(user, id))
                .map(ProjectResponse::of);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Mono<Void> deleteProjectByUserAndId(Mono<Principal> principalMono,
                                                          @PathVariable String id) {
        return principalMono
                .flatMap(principal -> usersService.findByEmailOrPhone(principal.getName()))
                .flatMap(user -> projectsService.deleteByUserAndId(user, id));
    }

    @PostMapping
    public Mono<ResponseEntity<?>> createByUser(Mono<Principal> principalMono,
                                                @Valid @RequestBody Mono<CreateProjectRequest> createProjectRequestMono) {

        Mono<User> userMono = principalMono
                .flatMap(principal -> usersService.findByEmailOrPhone(principal.getName()));

        return createProjectRequestMono
                .flatMap(createProjectRequest -> projectsService.existsByApiKey(createProjectRequest.getApiKey())
                        .flatMap(exist -> {
                            if (exist) {
                                return Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST));
                            } else {
                                return userMono
                                        .flatMap(user -> projectsService.create(user, createProjectRequest))
                                        .flatMap(project -> Mono.just(ResponseEntity.status(HttpStatus.CREATED).body(ProjectResponse.of(project))));
                            }
                        })
                );
    }

    @PutMapping
    public Mono<ResponseEntity<?>> updateByUser(Mono<Principal> principalMono,
                                                @Valid @RequestBody Mono<UpdateProjectRequest> updateProjectRequestMono) {

        Mono<User> userMono = principalMono
                .flatMap(principal -> usersService.findByEmailOrPhone(principal.getName()));

        return updateProjectRequestMono
                .flatMap(updateProjectRequest -> userMono
                        .flatMap(user -> projectsService.findByUserAndId(user, updateProjectRequest.getId())
                                .flatMap(project -> projectsService.update(project, updateProjectRequest))
                                .map(project -> ResponseEntity.status(HttpStatus.ACCEPTED).body(ProjectResponse.of(project)))
                        )
                );
    }
}
