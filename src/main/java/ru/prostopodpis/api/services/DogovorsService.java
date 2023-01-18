package ru.prostopodpis.api.services;

import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.prostopodpis.api.entity.Dogovor;
import ru.prostopodpis.api.entity.User;

/**
 * author: Sergey Khaylov
 * email: <a href="mailto:info@proextlab.ru">info@proextlab.ru</a>
 * org: ProextLab
 * site: <a href="https://proextlab.ru">ProextLab</a>
 * created: 04.10.2022
 **/

public interface DogovorsService {
    Mono<Dogovor> uploadDogovor(User user, Mono<FilePart> dogovorMono);
    Flux<Dogovor> findByUser(User user);

    Mono<Dogovor> findByUserAndId(User user, String id);
}
