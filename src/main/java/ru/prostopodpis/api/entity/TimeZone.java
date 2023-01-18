package ru.prostopodpis.api.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * author: Sergey Khaylov
 * email: <a href="mailto:info@proextlab.ru">info@proextlab.ru</a>
 * org: ProextLab
 * site: <a href="https://proextlab.ru">ProextLab</a>
 * created: 05.10.2022
 **/

@TypeAlias("TimeZone")
@Document(collection = "timezones")
@Data
@NoArgsConstructor
public class TimeZone {
    @Id
    private String id;
    private String name;
    private Integer gmtDiff = 0;

    public TimeZone(String name, Integer gmtDiff) {
        this.name = name;
        this.gmtDiff = gmtDiff;
    }
}
