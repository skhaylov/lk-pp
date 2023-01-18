package ru.prostopodpis.api.services;

import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import ru.prostopodpis.api.entity.User;

import java.security.Principal;

/**
 * author: Sergey Khaylov
 * email: <a href="mailto:info@proextlab.ru">info@proextlab.ru</a>
 * org: ProextLab
 * site: <a href="https://proextlab.ru">ProextLab</a>
 * created: 01.10.2022
 **/

public interface PaymentSystemCallbackService {
    Mono<?> process(ServerWebExchange serverWebExchange);
}
