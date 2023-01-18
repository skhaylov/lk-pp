package ru.prostopodpis.api.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

/**
 * author: Sergey Khaylov
 * email: <a href="mailto:info@proextlab.ru">info@proextlab.ru</a>
 * org: ProextLab
 * site: <a href="https://proextlab.ru">ProextLab</a>
 * created: 12.09.2022
 **/

@TypeAlias("Project")
@Document(collection = "projects")
@Data
public class Project {
    @Id
    private String id;

    private String name;
    private String description;

    private String callbackUrl;

    @Indexed(unique = true)
    private String apiKey;

    @Indexed
    private String userId;

    @Indexed
    private String sender;

    @CreatedDate
    private Instant createdDate;

    @LastModifiedDate
    private Instant lastModifiedDate;
}
