package ru.prostopodpis.api.dtos.senders;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * author: Sergey Khaylov
 * email: <a href="mailto:info@proextlab.ru">info@proextlab.ru</a>
 * org: ProextLab
 * site: <a href="https://proextlab.ru">ProextLab</a>
 * created: 13.09.2022
 **/

@Data
public class UpdateSenderRequest {
    @NotEmpty
    private String id;
    @NotEmpty
    private String name;
}
