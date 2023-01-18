package ru.prostopodpis.api.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * author: Sergey Khaylov
 * email: <a href="mailto:info@proextlab.ru">info@proextlab.ru</a>
 * org: ProextLab
 * site: <a href="https://proextlab.ru">ProextLab</a>
 * created: 18.09.2022
 **/

@TypeAlias("History")
@Document(collection = "history")
@Data
public class History {
    @Id
    private String id;
    private String recipient;
    private String message;
    private Date sentDate;
    private String status;
}
