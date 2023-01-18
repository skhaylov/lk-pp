package ru.prostopodpis.api.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * author: Sergey Khaylov
 * email: <a href="mailto:info@proextlab.ru">info@proextlab.ru</a>
 * org: ProextLab
 * site: <a href="https://proextlab.ru">ProextLab</a>
 * created: 13.09.2022
 **/

@TypeAlias("AppealType")
@Document(collection = "appeal_types")
@Data
public class AppealType {
    @Id
    private String id;

    private String name;
}
