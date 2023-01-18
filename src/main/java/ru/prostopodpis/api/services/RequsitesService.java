package ru.prostopodpis.api.services;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.prostopodpis.api.dtos.admin.requisites.AdminRequisiteResponse;
import ru.prostopodpis.api.dtos.requsites.RequsiteUpdateDto;
import ru.prostopodpis.api.entity.Requsite;
import ru.prostopodpis.api.entity.User;

/**
 * author: Sergey Khaylov
 * email: <a href="mailto:info@proextlab.ru">info@proextlab.ru</a>
 * org: ProextLab
 * site: <a href="https://proextlab.ru">ProextLab</a>
 * created: 11.09.2022
 **/

public interface RequsitesService {
    Mono<Requsite> findByUser(User user);

    Mono<Requsite> update(User user, RequsiteUpdateDto requsiteUpdateDto);

    Flux<Requsite> findAll();
}
