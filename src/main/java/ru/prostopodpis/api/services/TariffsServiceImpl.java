package ru.prostopodpis.api.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.prostopodpis.api.entity.Tariff;
import ru.prostopodpis.api.repositories.ReactiveTariffsRepository;

import javax.annotation.PostConstruct;

/**
 * author: Sergey Khaylov
 * email: <a href="mailto:info@proextlab.ru">info@proextlab.ru</a>
 * org: ProextLab
 * site: <a href="https://proextlab.ru">ProextLab</a>
 * created: 15.09.2022
 **/

@Service
@RequiredArgsConstructor
public class TariffsServiceImpl implements TariffsService {
    private final ReactiveTariffsRepository repository;
    @Override
    public Flux<Tariff> findAll() {
        return repository.findAll();
    }

    @Override
    public Mono<Tariff> findById(String tariffId) {
        return repository.findById(tariffId);
    }
}
