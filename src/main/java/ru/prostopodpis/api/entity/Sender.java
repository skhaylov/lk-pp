package ru.prostopodpis.api.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * author: Sergey Khaylov
 * email: <a href="mailto:info@proextlab.ru">info@proextlab.ru</a>
 * org: ProextLab
 * site: <a href="https://proextlab.ru">ProextLab</a>
 * created: 13.09.2022
 **/

@TypeAlias("Sender")
@Document(collection = "senders")
@Data
public class Sender {
    @Id
    private String id;

    private String name;

    @Indexed
    private Boolean active = false;

    @Indexed
    private String userId;

    private String telegramToken;
    private String telegramBotName;
}
