package ru.prostopodpis.api.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import ru.prostopodpis.api.entity.AppealType;

/**
 * author: Sergey Khaylov
 * email: <a href="mailto:info@proextlab.ru">info@proextlab.ru</a>
 * org: ProextLab
 * site: <a href="https://proextlab.ru">ProextLab</a>
 * created: 13.09.2022
 **/

public interface AppealTypesRepository extends ReactiveMongoRepository<AppealType, String> {
    Flux<AppealType> findAll();
}
