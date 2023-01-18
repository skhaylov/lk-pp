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
 * created: 11.09.2022
 **/

@TypeAlias("Requsite")
@Document(collection = "requsites")
@Data
public class Requsite {
    @Id
    private String id;

    private String orgName;
    private String address;
    private String postAddress;
    private String inn;
    private String kpp;
    private String ogrn;
    private String rs;
    private String bankName;
    private String bik;
    private String ks;
    private String director;
    private String osnovanie;
    private String phone;
    private String buhEmail;
    private String actEmail;
    private String additional;

    @Indexed
    private String userId;

    @CreatedDate
    private Instant createdDate;

    @LastModifiedDate
    private Instant lastModifiedDate;
}
