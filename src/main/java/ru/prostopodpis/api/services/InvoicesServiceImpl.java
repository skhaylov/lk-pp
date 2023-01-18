package ru.prostopodpis.api.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.prostopodpis.api.entity.Invoice;
import ru.prostopodpis.api.entity.User;
import ru.prostopodpis.api.repositories.InvoicesRepository;

import javax.annotation.PostConstruct;

/**
 * author: Sergey Khaylov
 * email: <a href="mailto:info@proextlab.ru">info@proextlab.ru</a>
 * org: ProextLab
 * site: <a href="https://proextlab.ru">ProextLab</a>
 * created: 01.10.2022
 **/

@Service
@RequiredArgsConstructor
@Log4j2
public class InvoicesServiceImpl implements InvoicesService {

    private final InvoicesRepository invoicesRepository;

    @Override
    public Mono<Invoice> create(User user, Float amount) {
        Invoice invoice = new Invoice();
        invoice.setUserId(user.getId());
        invoice.setAmount(amount);
        return invoicesRepository.save(invoice);
    }

    @Override
    public Mono<Invoice> findByInvoiceId(String invoiceId) {
        return invoicesRepository.findById(invoiceId);
    }

    @Override
    public Mono<Invoice> save(Invoice invoice) {
        return invoicesRepository.save(invoice);
    }

    @Override
    public Flux<Invoice> findByUser(User user) {
        return invoicesRepository.findByUserId(user.getId());
    }
}
