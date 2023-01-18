package ru.prostopodpis.api.dtos.accounts;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * author: Sergey Khaylov
 * email: <a href="mailto:info@proextlab.ru">info@proextlab.ru</a>
 * org: ProextLab
 * site: <a href="https://proextlab.ru">ProextLab</a>
 * created: 15.09.2022
 **/

@Data
public class AccountUpdateRequest {
    @NotEmpty
    private String email;

    @NotEmpty
    private String phone;

    private String password;

    @NotEmpty
    private String orgName;

    private String timezone;

    private String defaultSender;
}
