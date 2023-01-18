package ru.prostopodpis.api.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.prostopodpis.api.dtos.project_recipients.CreateProjectRecipientRequest;
import ru.prostopodpis.api.dtos.project_recipients.ProjectRecipientDto;
import ru.prostopodpis.api.dtos.project_recipients.ProjectRecipientFindRequest;
import ru.prostopodpis.api.entity.Project;
import ru.prostopodpis.api.entity.User;
import ru.prostopodpis.api.services.*;

import javax.validation.Valid;
import java.security.Principal;

/**
 * author: Sergey Khaylov
 * email: <a href="mailto:info@proextlab.ru">info@proextlab.ru</a>
 * org: ProextLab
 * site: <a href="https://proextlab.ru">ProextLab</a>
 * created: 17.09.2022
 **/

@RestController
@RequestMapping("/api/v1/project-recipients")
@RequiredArgsConstructor
@Log4j2
public class ProjectRecipientController {
    public static final int QRCODE_HEIGHT = 250;
    public static final int QRCODE_WIDTH = 250;
    private final ProjectRecipientService projectRecipientService;
    private final UsersService usersService;
    private final ProjectsService projectsService;
    private final SendersService sendersService;
    private final QRCodeGenerator qrCodeGenerator;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ProjectRecipientDto> create(Mono<Principal> principalMono,
                                            @Valid @RequestBody CreateProjectRecipientRequest createRequest) {

        Mono<User> userMono = principalMono
                .flatMap(principal -> usersService.findByEmailOrPhone(principal.getName()));

        Mono<Project> projectMono = Mono.empty();

        if (createRequest.getProjectId() != null && !createRequest.getProjectId().isEmpty()) {
            projectMono = userMono
                    .flatMap(user -> projectsService.findByUserAndId(user, createRequest.getProjectId()));

        }
        if (createRequest.getApiKey() != null && !createRequest.getApiKey().isEmpty()) {
            projectMono = userMono
                    .flatMap(user -> projectsService.findByUserAndApiKey(user, createRequest.getApiKey()));
        }

        return projectMono
                .flatMap(project ->
                        sendersService.findById(project.getSender())
                                .flatMap(sender -> projectRecipientService.create(project, createRequest)
                                        .map(projectRecipient -> ProjectRecipientDto.of(projectRecipient, sender))
                                )
                );
    }

    @GetMapping
    public Flux<ProjectRecipientDto> findAll(Mono<Principal> principalMono) {
        return principalMono
                .flatMap(principal -> usersService.findByEmailOrPhone(principal.getName()))
                .flatMapMany(projectsService::findAllByUser)
                .flatMap(project -> projectRecipientService.findByProject(project)
                        .flatMap(projectRecipient -> sendersService.findById(project.getSender())
                                .map(sender -> ProjectRecipientDto.of(projectRecipient, sender)))
                );
    }

    @PostMapping("/find")
    @ResponseStatus(HttpStatus.OK)
    public Flux<ProjectRecipientDto> find(Mono<Principal> principalMono,
                                          @Valid @RequestBody ProjectRecipientFindRequest findRequest) {
        return Flux.empty();
    }

    @GetMapping(value = "/qrcode/invite/{id}", produces = {MediaType.IMAGE_PNG_VALUE})
    @ResponseBody
    public Mono<byte[]> generateInviteQRCode(Mono<Principal> principalMono, @PathVariable String id) {
        return projectRecipientService.findById(id)
            .flatMap(projectRecipientService::generateJoinUrl)
            .flatMap(joinUrl -> {
                        try {
                            return Mono.just(qrCodeGenerator.generateQRCode(joinUrl, QRCODE_WIDTH, QRCODE_HEIGHT));
                        } catch (Exception e) {
                            log.info("Fail to generate invite qr code {}", joinUrl);
                            return Mono.just(new byte[]{});
                        }
                    }
            );
    }
}
