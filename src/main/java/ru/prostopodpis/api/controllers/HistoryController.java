package ru.prostopodpis.api.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.prostopodpis.api.dtos.history.HistoryResponseDto;
import ru.prostopodpis.api.services.*;

import java.security.Principal;

/**
 * author: Sergey Khaylov
 * email: <a href="mailto:info@proextlab.ru">info@proextlab.ru</a>
 * org: ProextLab
 * site: <a href="https://proextlab.ru">ProextLab</a>
 * created: 18.09.2022
 **/

@RestController
@RequestMapping("/api/v1/history")
@RequiredArgsConstructor
public class HistoryController {
    private final HistoryService historyService;
    private final UsersService usersService;
    private final ProjectsService projectsService;
    private final ProjectRecipientService projectRecipientService;
    private final InvoicesService invoicesService;

    @GetMapping
    public Flux<HistoryResponseDto> findAllByUser(
            Mono<Principal> principalMono,
            @RequestParam(required = false, defaultValue = "0") Integer count
    ) {
        Flux<HistoryResponseDto> fluxHistory = principalMono
                .flatMap(principal -> usersService.findByEmailOrPhone(principal.getName()))
                .flatMapMany(user -> projectsService.findAllByUser(user)
                        .flatMap(projectRecipientService::findByProject)
                        .flatMap(historyService::findAllByRecipient)
                        .map(HistoryResponseDto::of)
                        .concatWith(invoicesService.findByUser(user)
                                .map(HistoryResponseDto::of))
                        .sort((h1, h2) -> {
                            if (h1.getSentDate().before(h2.getSentDate())) return 1;
                            if (h1.getSentDate().after(h2.getSentDate())) return -1;
                            return 0;
                        })
                );
        if (count == 0) {
            return fluxHistory;
        } else {
            return fluxHistory.take(count);
        }
    }
}
