package ru.prostopodpis.api.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.prostopodpis.api.dtos.tariffs.ActivateTariffRequest;
import ru.prostopodpis.api.dtos.tariffs.TariffResponse;
import ru.prostopodpis.api.services.TariffsService;
import ru.prostopodpis.api.services.UsersService;

import javax.validation.Valid;
import java.security.Principal;

/**
 * author: Sergey Khaylov
 * email: <a href="mailto:info@proextlab.ru">info@proextlab.ru</a>
 * org: ProextLab
 * site: <a href="https://proextlab.ru">ProextLab</a>
 * created: 15.09.2022
 **/

@RestController
@RequestMapping("/api/v1/tariffs")
@RequiredArgsConstructor
@Log4j2
public class TariffController {
    private final TariffsService tariffsService;
    private final UsersService usersService;

    @GetMapping
    public Flux<TariffResponse> findAll() {
        return tariffsService.findAll()
                .map(TariffResponse::of);
    }

    @PutMapping("/activate")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Mono<Void> activateTariff(Mono<Principal> principalMono,
                                     @Valid @RequestBody ActivateTariffRequest activateTariffRequest) {
        return principalMono
                .flatMap(principal -> usersService.findByEmailOrPhone(principal.getName()))
                .flatMap(user ->
                        usersService.activateTariff(user, activateTariffRequest.getTariffId())
                                .flatMap(consumer -> tariffsService.findById(activateTariffRequest.getTariffId()))
                                .flatMap(tariff -> usersService.decreaseBalance(user, tariff.getMonthCost()))
                )
                .then();
    }

    @PutMapping("/can-activate")
    public Mono<ResponseEntity<Void>> canActivateTariff(Mono<Principal> principalMono,
                                                        @Valid @RequestBody ActivateTariffRequest activateTariffRequest) {
        return principalMono
                .flatMap(principal -> usersService.findByEmailOrPhone(principal.getName()))
                .flatMap(user -> tariffsService.findById(activateTariffRequest.getTariffId())
                        .flatMap(tariff -> usersService.canActivateTariff(user, tariff)))
                .map(canActivate -> {
                    log.info("canActivate {}", canActivate);
                    if (canActivate) {
                        return ResponseEntity.ok().build();
                    } else {
                        return ResponseEntity.badRequest().build();
                    }
                });
    }
}
