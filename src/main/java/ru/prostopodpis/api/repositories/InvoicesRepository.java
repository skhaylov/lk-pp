package ru.prostopodpis.api.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import ru.prostopodpis.api.entity.Invoice;

/**
 * author: Sergey Khaylov
 * email: <a href="mailto:info@proextlab.ru">info@proextlab.ru</a>
 * org: ProextLab
 * site: <a href="https://proextlab.ru">ProextLab</a>
 * created: 01.10.2022
 **/

public interface InvoicesRepository extends ReactiveMongoRepository<Invoice, String> {
    Flux<Invoice> findByUserId(String userId);
}
