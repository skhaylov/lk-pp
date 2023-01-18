package ru.prostopodpis.api.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import ru.prostopodpis.api.dtos.messages.SendMessageRequest;
import ru.prostopodpis.api.services.MessagesService;
import ru.prostopodpis.api.services.ProjectsService;
import ru.prostopodpis.api.services.UsersService;

import javax.validation.Valid;
import java.security.Principal;

/**
 * author: Sergey Khaylov
 * email: <a href="mailto:info@proextlab.ru">info@proextlab.ru</a>
 * org: ProextLab
 * site: <a href="https://proextlab.ru">ProextLab</a>
 * created: 16.09.2022
 **/


@RestController
@RequestMapping("/api/v1/messages")
@RequiredArgsConstructor
public class MessageController {

    public static final float MESSAGE_COST = 0.3f;

    private final MessagesService messagesService;
    private final UsersService usersService;
    private final ProjectsService projectsService;

    @PostMapping("/send")
    public Mono<Void> sendMessage(Mono<Principal> principalMono,
                                  @Valid @RequestBody SendMessageRequest sendMessageRequest) {
        return principalMono
                .flatMap(principal -> usersService.findByEmailOrPhone(principal.getName()))
                .filterWhen(user -> Mono.just(user.getBalance() >= MESSAGE_COST))
                .flatMap(user -> projectsService
                        .findByUserAndApiKey(user, sendMessageRequest.getApiKey())
                        .flatMap(project -> messagesService
                                .send(sendMessageRequest.getApiKey(), sendMessageRequest.getRecipientId(), user.getId(), sendMessageRequest.getMessage())
                                .then()
                ).then())
                .then();
    }
}
