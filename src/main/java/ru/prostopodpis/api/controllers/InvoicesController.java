package ru.prostopodpis.api.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import ru.prostopodpis.api.dtos.invoices.CreateInvoiceRequest;
import ru.prostopodpis.api.dtos.invoices.InvoiceCreateResponse;
import ru.prostopodpis.api.services.InvoicesService;
import ru.prostopodpis.api.services.UsersService;

import javax.validation.Valid;
import java.security.Principal;

/**
 * author: Sergey Khaylov
 * email: <a href="mailto:info@proextlab.ru">info@proextlab.ru</a>
 * org: ProextLab
 * site: <a href="https://proextlab.ru">ProextLab</a>
 * created: 30.09.2022
 **/

@RestController
@RequestMapping("/api/v1/invoices")
@RequiredArgsConstructor
public class InvoicesController {

    private final InvoicesService invoicesService;
    private final UsersService usersService;

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Mono<InvoiceCreateResponse> create(Mono<Principal> principalMono,
                                              @Valid @RequestBody CreateInvoiceRequest data) {
        return principalMono
                .flatMap(principal -> usersService.findByEmailOrPhone(principal.getName()))
                .flatMap(user -> invoicesService.create(user, data.getAmount()))
                .map(InvoiceCreateResponse::of);
    }
}
