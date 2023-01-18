package ru.prostopodpis.api.services;

import reactor.core.publisher.Mono;

/**
 * author: Sergey Khaylov
 * email: <a href="mailto:info@proextlab.ru">info@proextlab.ru</a>
 * org: ProextLab
 * site: <a href="https://proextlab.ru">ProextLab</a>
 * created: 16.09.2022
 **/

public interface MessagesService {
    Mono<Void> send(String apiKey, String recipientId, String userId, String message);
}
