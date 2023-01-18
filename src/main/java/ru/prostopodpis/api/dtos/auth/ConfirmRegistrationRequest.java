package ru.prostopodpis.api.dtos.auth;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * author: Sergey Khaylov
 * email: <a href="mailto:info@proextlab.ru">info@proextlab.ru</a>
 * org: ProextLab
 * site: <a href="https://proextlab.ru">ProextLab</a>
 * created: 14.09.2022
 **/

@Data
public class ConfirmRegistrationRequest {
    @NotEmpty
    private String code;
}
