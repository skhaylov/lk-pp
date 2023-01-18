package ru.prostopodpis.api.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.prostopodpis.api.entity.Sender;
import ru.prostopodpis.api.entity.User;
import ru.prostopodpis.api.repositories.ReactiveProjectsRepository;
import ru.prostopodpis.api.repositories.ReactiveSendersRepository;

import javax.annotation.PostConstruct;

/**
 * author: Sergey Khaylov
 * email: <a href="mailto:info@proextlab.ru">info@proextlab.ru</a>
 * org: ProextLab
 * site: <a href="https://proextlab.ru">ProextLab</a>
 * created: 13.09.2022
 **/

@Service
@RequiredArgsConstructor
@Log4j2
public class SendersServiceImpl implements SendersService {
    private final ReactiveSendersRepository sendersRepository;
    private final ReactiveProjectsRepository projectsRepository;

    @Override
    public Flux<Sender> findAllByUser(User user) {
        return sendersRepository.findByUserId(user.getId());
    }
    @Override
    public Flux<Sender> findAllByUserWithDefault(User user) {
        return findAllByUser(user)
                .concatWith(sendersRepository.findByUserIdIsNull());
    }

    @Override
    public Mono<Sender> create(User user, String name) {
        Sender sender = new Sender();
        sender.setName(name);
        sender.setUserId(user.getId());
        return sendersRepository.save(sender);
    }

    @Override
    public Mono<Sender> update(User user, String id, String name) {
        return sendersRepository
                .findByUserIdAndId(user.getId(), id)
                .flatMap(sender -> {
                    sender.setName(name);
                    return sendersRepository.save(sender);
                });
    }

    @Override
    public Mono<Sender> findByUserAndId(User user, String id) {
        return sendersRepository.findByUserIdAndId(user.getId(), id);
    }

    @Override
    public Mono<Void> delete(User user, String id) {
        return projectsRepository.findByUserIdOrderByCreatedDate(user.getId())
                .filter(project -> project.getSender().equals(id))
                .flatMap(project -> {
                    project.setSender(null);
                    return projectsRepository.save(project);
                })
                .then(sendersRepository.deleteByUserIdAndId(user.getId(), id));
    }

    @Override
    public Mono<Sender> findById(String id) {
        return sendersRepository.findById(id);
    }
}
