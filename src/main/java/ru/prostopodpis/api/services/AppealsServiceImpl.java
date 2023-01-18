package ru.prostopodpis.api.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.prostopodpis.api.entity.Appeal;
import ru.prostopodpis.api.entity.User;
import ru.prostopodpis.api.repositories.ReactiveAppealsRepository;

/**
 * author: Sergey Khaylov
 * email: <a href="mailto:info@proextlab.ru">info@proextlab.ru</a>
 * org: ProextLab
 * site: <a href="https://proextlab.ru">ProextLab</a>
 * created: 05.10.2022
 **/

@Service
@RequiredArgsConstructor
public class AppealsServiceImpl implements AppealsService {
    private final ReactiveAppealsRepository repository;

    @Override
    public Mono<Appeal> create(User user, String typeId, String message) {
        Appeal appeal = new Appeal();
        appeal.setTypeId(typeId);
        appeal.setUserId(user.getId());
        appeal.setMessage(message);
        return repository.save(appeal);
    }

    @Override
    public Flux<Appeal> findByUser(User user) {
        return repository.findByUserId(user.getId());
    }

    @Override
    public Mono<Appeal> findByUserAndId(User user, String id) {
        return repository.findByUserIdAndId(user.getId(), id);
    }
}
