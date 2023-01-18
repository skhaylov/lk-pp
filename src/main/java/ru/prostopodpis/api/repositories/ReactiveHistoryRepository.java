package ru.prostopodpis.api.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import ru.prostopodpis.api.entity.History;

/**
 * author: Sergey Khaylov
 * email: <a href="mailto:info@proextlab.ru">info@proextlab.ru</a>
 * org: ProextLab
 * site: <a href="https://proextlab.ru">ProextLab</a>
 * created: 18.09.2022
 **/

public interface ReactiveHistoryRepository extends ReactiveMongoRepository<History, String> {
    Flux<History> findByRecipient(String recipient);
}
