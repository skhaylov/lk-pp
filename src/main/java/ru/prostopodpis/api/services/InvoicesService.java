package ru.prostopodpis.api.services;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.prostopodpis.api.dtos.history.HistoryResponseDto;
import ru.prostopodpis.api.entity.Invoice;
import ru.prostopodpis.api.entity.User;

/**
 * author: Sergey Khaylov
 * email: <a href="mailto:info@proextlab.ru">info@proextlab.ru</a>
 * org: ProextLab
 * site: <a href="https://proextlab.ru">ProextLab</a>
 * created: 01.10.2022
 **/

public interface InvoicesService {
    Mono<Invoice> create(User user, Float amount);

    Mono<Invoice> findByInvoiceId(String invoiceId);

    Mono<Invoice> save(Invoice invoice);

    Flux<Invoice> findByUser(User user);
}
