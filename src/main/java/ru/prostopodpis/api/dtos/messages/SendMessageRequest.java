package ru.prostopodpis.api.dtos.messages;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * author: Sergey Khaylov
 * email: <a href="mailto:info@proextlab.ru">info@proextlab.ru</a>
 * org: ProextLab
 * site: <a href="https://proextlab.ru">ProextLab</a>
 * created: 16.09.2022
 **/

@Data
public class SendMessageRequest {
    @NotNull
    @NotEmpty
    private String message;

    @NotNull
    @NotEmpty
    private String recipientId;

    @NotNull
    @NotEmpty
    private String apiKey;
}
