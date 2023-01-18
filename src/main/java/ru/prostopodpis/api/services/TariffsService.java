package ru.prostopodpis.api.services;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.prostopodpis.api.entity.Tariff;

/**
 * author: Sergey Khaylov
 * email: <a href="mailto:info@proextlab.ru">info@proextlab.ru</a>
 * org: ProextLab
 * site: <a href="https://proextlab.ru">ProextLab</a>
 * created: 15.09.2022
 **/

public interface TariffsService {
    Flux<Tariff> findAll();

    Mono<Tariff> findById(String tariffId);
}
