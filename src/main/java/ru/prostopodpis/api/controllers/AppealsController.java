package ru.prostopodpis.api.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.prostopodpis.api.dtos.appeals.CreateAppealDto;
import ru.prostopodpis.api.dtos.appeals.AppealResponseDto;
import ru.prostopodpis.api.entity.AppealType;
import ru.prostopodpis.api.services.AppealTypesService;
import ru.prostopodpis.api.services.AppealsService;
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
@RequestMapping("/api/v1/appeals")
@RequiredArgsConstructor
public class AppealsController {

    private final AppealTypesService appealTypesService;
    private final AppealsService appealsService;
    private final UsersService usersService;

    @GetMapping("/types")
    public Flux<AppealType> findAllTypes() {
        return appealTypesService.findAll();
    }

    @GetMapping
    public Flux<AppealResponseDto> findAllByUser(Mono<Principal> principalMono) {
        return principalMono
                .flatMap(principal -> usersService.findByEmailOrPhone(principal.getName()))
                .flatMapMany(appealsService::findByUser)
                .sort((a1, a2) -> {
                    if (a1.getCreatedDate().after(a2.getCreatedDate())) {
                        return 1;
                    }
                    if (a1.getCreatedDate().before(a2.getCreatedDate())) {
                        return -1;
                    }
                    return 0;
                })
                .map(AppealResponseDto::of);
    }

    @PostMapping
    public Mono<ResponseEntity<AppealResponseDto>> create(Mono<Principal> principalMono,
                                                          @Valid @RequestBody CreateAppealDto data) {
        return principalMono
                .flatMap(principal -> usersService.findByEmailOrPhone(principal.getName()))
                .flatMap(user -> appealsService.create(user, data.getTypeId(), data.getMessage()))
                .map(appeal -> ResponseEntity.status(HttpStatus.CREATED).body(AppealResponseDto.of(appeal)))
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<AppealResponseDto>> findByUserAndId(Mono<Principal> principalMono,
                                                                   @PathVariable String id) {
        return principalMono
                .flatMap(principal -> usersService.findByEmailOrPhone(principal.getName()))
                .flatMap(user -> appealsService.findByUserAndId(user, id))
                .map(appeal -> ResponseEntity.ok(AppealResponseDto.of(appeal)))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

}
