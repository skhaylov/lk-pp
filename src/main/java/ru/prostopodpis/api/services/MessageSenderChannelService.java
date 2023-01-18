package ru.prostopodpis.api.services;

import reactor.core.publisher.Mono;
import ru.prostopodpis.api.entity.Sender;

/**
 * author: Sergey Khaylov
 * email: <a href="mailto:info@proextlab.ru">info@proextlab.ru</a>
 * org: ProextLab
 * site: <a href="https://proextlab.ru">ProextLab</a>
 * created: 16.09.2022
 **/

public interface MessageSenderChannelService {
    Mono<Void> send(String senderId, String recipientId,  String userId, String message);

    boolean applicableForSender(Sender sender);
}
