package ru.prostopodpis.api.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.prostopodpis.api.dtos.senders.CreateSenderRequest;
import ru.prostopodpis.api.dtos.senders.SenderResponse;
import ru.prostopodpis.api.dtos.senders.UpdateSenderRequest;
import ru.prostopodpis.api.services.SendersService;
import ru.prostopodpis.api.services.UsersService;

import javax.validation.Valid;
import java.security.Principal;

/**
 * author: Sergey Khaylov
 * email: <a href="mailto:info@proextlab.ru">info@proextlab.ru</a>
 * org: ProextLab
 * site: <a href="https://proextlab.ru">ProextLab</a>
 * created: 13.09.2022
 **/

@RestController
@RequestMapping("/api/v1/senders")
@RequiredArgsConstructor
public class SendersController {
    private final SendersService sendersService;
    private final UsersService usersService;

    @GetMapping()
    public Flux<SenderResponse> findAllByUser(Mono<Principal> principalMono) {
        return principalMono
                .flatMap(principal -> usersService.findByEmailOrPhone(principal.getName()))
                .flatMapMany(sendersService::findAllByUserWithDefault)
                .map(SenderResponse::of);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<SenderResponse> create(Mono<Principal> principalMono,
                                       @Valid @RequestBody CreateSenderRequest createSenderRequest) {
        return principalMono
                .flatMap(principal -> usersService.findByEmailOrPhone(principal.getName()))
                .flatMap(user -> sendersService.create(user, createSenderRequest.getName()))
                .map(SenderResponse::of);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Mono<SenderResponse> update(Mono<Principal> principalMono,
                                       @Valid @RequestBody UpdateSenderRequest updateSenderRequest) {
        return principalMono
                .flatMap(principal -> usersService.findByEmailOrPhone(principal.getName()))
                .flatMap(user -> sendersService.update(user, updateSenderRequest.getId(), updateSenderRequest.getName()))
                .map(SenderResponse::of);
    }

    @GetMapping("/{id}")
    public Mono<SenderResponse> findByUserAndId(Mono<Principal> principalMono,
                                                @PathVariable String id) {
        return principalMono
                .flatMap(principal -> usersService.findByEmailOrPhone(principal.getName()))
                .flatMap(user -> sendersService.findByUserAndId(user, id))
                .map(SenderResponse::of);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Mono<Void> deleteByUserAndId(Mono<Principal> principalMono,
                                        @PathVariable String id) {
        return principalMono
                .flatMap(principal -> usersService.findByEmailOrPhone(principal.getName()))
                .flatMap(user -> sendersService.delete(user, id))
                .then();
    }
}
