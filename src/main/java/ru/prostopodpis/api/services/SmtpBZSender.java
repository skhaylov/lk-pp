package ru.prostopodpis.api.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.prostopodpis.api.configurations.SmtpBzConfiguration;

import java.util.HashMap;

/**
 * author: Sergey Khaylov
 * email: <a href="mailto:info@proextlab.ru">info@proextlab.ru</a>
 * org: ProextLab
 * site: <a href="https://proextlab.ru">ProextLab</a>
 * created: 13.09.2022
 **/

@Service
@RequiredArgsConstructor
@Log4j2
public class SmtpBZSender {
    @Autowired
    private SmtpBzConfiguration smtpBzConfiguration;

    private final RestTemplate restTemplate = new RestTemplate();

    public void send(String recipient, String subject, String message) {

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, smtpBzConfiguration.getToken());
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<MultiValueMap<String, Object>>(
                new LinkedMultiValueMap<>() {{
                    add("from", smtpBzConfiguration.getSenderEmail());
                    add("to", recipient);
                    add("subject", subject);
                    add("html", message);
                }},
                headers
        );

        restTemplate.postForObject(
                smtpBzConfiguration.getApi() + "/smtp/send",
                httpEntity,
                String.class
        );
    }
}
