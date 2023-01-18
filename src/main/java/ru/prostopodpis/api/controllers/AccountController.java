package ru.prostopodpis.api.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import ru.prostopodpis.api.dtos.accounts.AccountResponse;
import ru.prostopodpis.api.dtos.accounts.AccountUpdateRequest;
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
@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
public class AccountController {
    private final UsersService usersService;

    @GetMapping
    public Mono<AccountResponse> getAccount(Mono<Principal> principalMono) {
        return principalMono
                .flatMap(principal -> usersService.findByEmailOrPhone(principal.getName()))
                .map(AccountResponse::of);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Mono<AccountResponse> update(Mono<Principal> principalMono,
                                        @Valid @RequestBody AccountUpdateRequest accountUpdateRequest) {
        return principalMono
                .flatMap(principal -> usersService.findByEmailOrPhone(principal.getName()))
                .flatMap(user -> usersService.updateAccount(user, accountUpdateRequest))
                .map(AccountResponse::of);
    }
}
