package ru.prostopodpis.api.services;

import reactor.core.publisher.Mono;
import ru.prostopodpis.api.entity.Tariff;
import ru.prostopodpis.api.entity.User;

/**
 * author: Sergey Khaylov
 * email: <a href="mailto:info@proextlab.ru">info@proextlab.ru</a>
 * org: ProextLab
 * site: <a href="https://proextlab.ru">ProextLab</a>
 * created: 15.09.2022
 **/

public interface TariffHistoryService {
    Mono<Void> changeTariff(User user, Tariff tariff);
    Mono<Void> changeTariff(String userId, String tariffId);
}
