package ru.prostopodpis.api.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ru.prostopodpis.api.dtos.timezones.TimeZoneResponseDto;
import ru.prostopodpis.api.entity.TimeZone;
import ru.prostopodpis.api.services.TimeZonesService;

import java.util.Comparator;

/**
 * author: Sergey Khaylov
 * email: <a href="mailto:info@proextlab.ru">info@proextlab.ru</a>
 * org: ProextLab
 * site: <a href="https://proextlab.ru">ProextLab</a>
 * created: 05.10.2022
 **/

@RestController
@RequestMapping("/api/v1/timezones")
@RequiredArgsConstructor
public class TimeZonesController {
    private final TimeZonesService timeZonesService;

    @GetMapping
    public Flux<TimeZoneResponseDto> findAll() {
        return timeZonesService
                .findAll()
                .sort(Comparator.comparing(TimeZone::getGmtDiff))
                .map(TimeZoneResponseDto::of);
    }
}
