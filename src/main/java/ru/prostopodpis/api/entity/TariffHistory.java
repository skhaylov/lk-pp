package ru.prostopodpis.api.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

/**
 * author: Sergey Khaylov
 * email: <a href="mailto:info@proextlab.ru">info@proextlab.ru</a>
 * org: ProextLab
 * site: <a href="https://proextlab.ru">ProextLab</a>
 * created: 15.09.2022
 **/

@TypeAlias("TariffHistory")
@Document(collection = "tariff_history")
@Data
public class TariffHistory {
    @Id
    private String id;
    @Indexed
    private String userId;
    @Indexed
    private String tariffId;
    @CreatedDate
    private Instant activationDate;
}
