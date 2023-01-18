package ru.prostopodpis.api.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.prostopodpis.api.enums.InvoiceStatusEnum;

import java.time.Instant;

/**
 * author: Sergey Khaylov
 * email: <a href="mailto:info@proextlab.ru">info@proextlab.ru</a>
 * org: ProextLab
 * site: <a href="https://proextlab.ru">ProextLab</a>
 * created: 01.10.2022
 **/

@TypeAlias("Invoice")
@Document(collection = "invoices")
@Data
public class Invoice {
    @Id
    private String id;

    @Indexed
    private String userId;

    private Float amount;

    private Object paymentSystemResult;

    @Indexed
    private InvoiceStatusEnum status = InvoiceStatusEnum.NEW;

    @CreatedDate
    private Instant createdDate;

    @LastModifiedDate
    private Instant lastModifiedDate;
}
