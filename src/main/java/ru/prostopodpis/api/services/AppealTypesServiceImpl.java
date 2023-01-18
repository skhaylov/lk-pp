package ru.prostopodpis.api.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import ru.prostopodpis.api.entity.AppealType;
import ru.prostopodpis.api.repositories.AppealTypesRepository;

/**
 * author: Sergey Khaylov
 * email: <a href="mailto:info@proextlab.ru">info@proextlab.ru</a>
 * org: ProextLab
 * site: <a href="https://proextlab.ru">ProextLab</a>
 * created: 13.09.2022
 **/

@Service
@RequiredArgsConstructor
public class AppealTypesServiceImpl implements AppealTypesService {
    private final AppealTypesRepository repository;

    @Override
    public Flux<AppealType> findAll() {
        return repository.findAll();
    }
}
