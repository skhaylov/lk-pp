package ru.prostopodpis.api.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.prostopodpis.api.dtos.scheta.CreateSchetRequestDto;
import ru.prostopodpis.api.dtos.scheta.SchetResponseDto;
import ru.prostopodpis.api.entity.Schet;
import ru.prostopodpis.api.services.SchetaService;
import ru.prostopodpis.api.services.UsersService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Comparator;

/**
 * author: Sergey Khaylov
 * email: <a href="mailto:info@proextlab.ru">info@proextlab.ru</a>
 * org: ProextLab
 * site: <a href="https://proextlab.ru">ProextLab</a>
 * created: 06.10.2022
 **/

@RestController
@RequestMapping("/api/v1/scheta")
@RequiredArgsConstructor
public class SchetaControllers {
    private final SchetaService schetaService;
    private final UsersService usersService;

    @GetMapping
    public Flux<SchetResponseDto> findAllByUser(Mono<Principal> principalMono) {
        return principalMono
                .flatMap(principal -> usersService.findByEmailOrPhone(principal.getName()))
                .flatMapMany(schetaService::findByUser)
                .sort(Comparator.comparing(Schet::getCreatedDate).reversed())
                .map(SchetResponseDto::of);
    }

    @GetMapping("/{id}")
    public Mono<SchetResponseDto> findByUserAndId(Mono<Principal> principalMono,
                                                  @PathVariable String id) {
        return principalMono
                .flatMap(principal -> usersService.findByEmailOrPhone(principal.getName()))
                .flatMap(user -> schetaService.findByUserAndId(user, id))
                .map(SchetResponseDto::of);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<SchetResponseDto> create(Mono<Principal> principalMono,
                                         @Valid @RequestBody CreateSchetRequestDto createData) {
        return principalMono
                .flatMap(principal -> usersService.findByEmailOrPhone(principal.getName()))
                .flatMap(user -> schetaService.create(user, createData.getAmount()))
                .map(SchetResponseDto::of);
    }
}
