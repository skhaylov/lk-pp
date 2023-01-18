package ru.prostopodpis.api.configurations;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * author: Sergey Khaylov
 * email: <a href="mailto:info@proextlab.ru">info@proextlab.ru</a>
 * org: ProextLab
 * site: <a href="https://proextlab.ru">ProextLab</a>
 * created: 13.09.2022
 **/

@Configuration
@ConfigurationProperties(prefix = "smtp-bz")
@Data
public class SmtpBzConfiguration {
    private String api;
    private String token;
    private String senderEmail;
}
