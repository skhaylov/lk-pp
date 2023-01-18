package ru.prostopodpis.api.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import ru.prostopodpis.api.entity.TimeZone;
import ru.prostopodpis.api.repositories.ReactiveTimeZonesRepository;

/**
 * author: Sergey Khaylov
 * email: <a href="mailto:info@proextlab.ru">info@proextlab.ru</a>
 * org: ProextLab
 * site: <a href="https://proextlab.ru">ProextLab</a>
 * created: 05.10.2022
 **/

@Service
@RequiredArgsConstructor
public class TimeZonesServiceImpl implements TimeZonesService {
    private final ReactiveTimeZonesRepository repository;

    @Override
    public Flux<TimeZone> findAll() {
        return repository.findAll();
    }
}
