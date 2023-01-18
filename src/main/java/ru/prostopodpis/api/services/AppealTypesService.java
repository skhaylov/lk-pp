package ru.prostopodpis.api.services;

import reactor.core.publisher.Flux;
import ru.prostopodpis.api.entity.AppealType;

/**
 * author: Sergey Khaylov
 * email: <a href="mailto:info@proextlab.ru">info@proextlab.ru</a>
 * org: ProextLab
 * site: <a href="https://proextlab.ru">ProextLab</a>
 * created: 13.09.2022
 **/

public interface AppealTypesService {
    Flux<AppealType> findAll();
}
