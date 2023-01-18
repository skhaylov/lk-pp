package ru.prostopodpis.api.services;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import ru.prostopodpis.api.entity.History;
import ru.prostopodpis.api.entity.ProjectRecipient;

/**
 * author: Sergey Khaylov
 * email: <a href="mailto:info@proextlab.ru">info@proextlab.ru</a>
 * org: ProextLab
 * site: <a href="https://proextlab.ru">ProextLab</a>
 * created: 18.09.2022
 **/

public interface HistoryService {
    Flux<History> findAllByRecipient(ProjectRecipient projectRecipient);
}
