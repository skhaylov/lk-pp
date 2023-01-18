package ru.prostopodpis.api.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import ru.prostopodpis.api.dtos.requsites.RequsiteResponseDto;
import ru.prostopodpis.api.dtos.requsites.RequsiteUpdateDto;
import ru.prostopodpis.api.services.RequsitesService;
import ru.prostopodpis.api.services.UsersService;

import javax.validation.Valid;
import java.security.Principal;

/**
 * author: Sergey Khaylov
 * email: <a href="mailto:info@proextlab.ru">info@proextlab.ru</a>
 * org: ProextLab
 * site: <a href="https://proextlab.ru">ProextLab</a>
 * created: 11.09.2022
 **/

@RestController
@RequestMapping("/api/v1/requsites")
@RequiredArgsConstructor
public class RequisiteController {

    private final RequsitesService requsitesService;
    private final UsersService usersService;

    @GetMapping
    public Mono<ResponseEntity<?>> getByUser(Mono<Principal> principalMono) {
        return principalMono
                .flatMap(principal -> usersService.findByEmailOrPhone(principal.getName()))
                .flatMap(requsitesService::findByUser)
                .flatMap(requsite -> {
                    if (requsite == null) {
                        return Mono.just(ResponseEntity.notFound().build());
                    } else {
                        return Mono.just(ResponseEntity.ok(RequsiteResponseDto.of(requsite)));
                    }
                });
    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Mono<RequsiteResponseDto> update(@Valid @RequestBody Mono<RequsiteUpdateDto> requsiteUpdateDtoMono,
                                            Mono<Principal> principalMono) {
        return principalMono
                .flatMap(principal -> usersService.findByEmailOrPhone(principal.getName()))
                .flatMap(user -> requsiteUpdateDtoMono.flatMap(
                        requsiteUpdateDto -> requsitesService
                                .update(user, requsiteUpdateDto)
                                .map(RequsiteResponseDto::of))
                );
    }
}
