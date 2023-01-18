package ru.prostopodpis.api.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import ru.prostopodpis.api.entity.History;
import ru.prostopodpis.api.entity.ProjectRecipient;
import ru.prostopodpis.api.repositories.ReactiveHistoryRepository;

/**
 * author: Sergey Khaylov
 * email: <a href="mailto:info@proextlab.ru">info@proextlab.ru</a>
 * org: ProextLab
 * site: <a href="https://proextlab.ru">ProextLab</a>
 * created: 18.09.2022
 **/

@Service
@RequiredArgsConstructor
public class HistoryServiceImpl implements HistoryService {
    private final ReactiveHistoryRepository repository;
    @Override
    public Flux<History> findAllByRecipient(ProjectRecipient projectRecipient) {
        return repository.findByRecipient(projectRecipient.getId());
    }
}
