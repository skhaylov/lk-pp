package ru.prostopodpis.api.repositories;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;
import ru.prostopodpis.api.entity.User;

/**
 * author: Sergey Khaylov
 * email: <a href="mailto:info@proextlab.ru">info@proextlab.ru</a>
 * org: ProextLab
 * site: <a href="https://proextlab.ru">ProextLab</a>
 * created: 11.09.2022
 **/

public interface ReactiveUserRepository extends ReactiveMongoRepository<User, String> {
    @Query(value = "{$or: [{email: ?0}, {phone: ?0}]}")
    Mono<User> findByEmailOrPhone(String value);

    Mono<User> findByEmailOrPhone(String email, String phone);

    Mono<Boolean> existsByEmailOrPhone(String email, String phone);

    Mono<User> findByConfirmCode(String code);
}
