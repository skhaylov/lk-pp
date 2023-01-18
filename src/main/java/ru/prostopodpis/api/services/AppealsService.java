package ru.prostopodpis.api.services;

import org.reactivestreams.Publisher;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.prostopodpis.api.dtos.appeals.AppealResponseDto;
import ru.prostopodpis.api.entity.Appeal;
import ru.prostopodpis.api.entity.User;

/**
 * author: Sergey Khaylov
 * email: <a href="mailto:info@proextlab.ru">info@proextlab.ru</a>
 * org: ProextLab
 * site: <a href="https://proextlab.ru">ProextLab</a>
 * created: 05.10.2022
 **/

public interface AppealsService {
    Mono<Appeal> create(User user, String typeId, String message);

    Flux<Appeal> findByUser(User user);

    Mono<Appeal> findByUserAndId(User user, String id);
}
