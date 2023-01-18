package ru.prostopodpis.api.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import ru.prostopodpis.api.domain.jwt.JwtTokenProvider;
import ru.prostopodpis.api.dtos.auth.ConfirmRegistrationRequest;
import ru.prostopodpis.api.dtos.auth.LoginRequest;
import ru.prostopodpis.api.dtos.auth.RegistrationRequest;
import ru.prostopodpis.api.dtos.auth.RestorePasswordRequest;
import ru.prostopodpis.api.services.UsersService;

import javax.validation.Valid;

/**
 * author: Sergey Khaylov
 * email: <a href="mailto:info@proextlab.ru">info@proextlab.ru</a>
 * org: ProextLab
 * site: <a href="https://proextlab.ru">ProextLab</a>
 * created: 11.09.2022
 **/

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UsersService usersService;
    private final JwtTokenProvider tokenProvider;
    private final ReactiveAuthenticationManager authenticationManager;

    @PostMapping("/login")
    public Mono<ResponseEntity<Object>> login(@Valid @RequestBody Mono<LoginRequest> loginRequestMono) {
        return loginRequestMono
                .flatMap(login -> this.authenticationManager
                        .authenticate(new UsernamePasswordAuthenticationToken(login.getLogin(), login.getPassword()))
                        .map(this.tokenProvider::createToken)
                )
                .map(jwt -> {
                    HttpHeaders httpHeaders = new HttpHeaders();
                    httpHeaders.add(HttpHeaders.AUTHORIZATION, "Bearer " + jwt);
                    return new ResponseEntity<>(httpHeaders, HttpStatus.OK);
                })
                .onErrorReturn(Exception.class, ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    @PostMapping("/register")
    public Mono<ResponseEntity<?>> register(@Valid @RequestBody Mono<RegistrationRequest> regRequest) {
        return regRequest
                .flatMap(reg -> usersService
                        .findByEmailOrPhone(reg.getEmail(), reg.getPhone())
                        .switchIfEmpty(usersService.create(reg.getPassword(), reg.getEmail(), reg.getPhone()))
                )
                .map(user -> {
                    if (user == null) {
                        return ResponseEntity.badRequest().build();
                    } else {
                        return new ResponseEntity<>(HttpStatus.CREATED);
                    }
                });
    }

    @PostMapping("/restore")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Mono<Void> restorePassword(@Valid @RequestBody Mono<RestorePasswordRequest> restorePasswordRequestMono) {
        return restorePasswordRequestMono
                .flatMap(restorePasswordRequest -> usersService.restorePassword(restorePasswordRequest.getLogin()));
    }

    @PostMapping("/confirm")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Mono<Void> confirmRegistration(@Valid @RequestBody Mono<ConfirmRegistrationRequest> confirmRegistrationRequestMono) {
        return confirmRegistrationRequestMono
                .flatMap(confirmRegistrationRequest -> usersService.confirmRegistration(confirmRegistrationRequest.getCode()));
    }
}
