package ru.prostopodpis.api.services;

import reactor.core.publisher.Flux;
import ru.prostopodpis.api.dtos.timezones.TimeZoneResponseDto;
import ru.prostopodpis.api.entity.TimeZone;


/**
 * author: Sergey Khaylov
 * email: <a href="mailto:info@proextlab.ru">info@proextlab.ru</a>
 * org: ProextLab
 * site: <a href="https://proextlab.ru">ProextLab</a>
 * created: 05.10.2022
 **/

public interface TimeZonesService {
    Flux<TimeZone> findAll();
}
