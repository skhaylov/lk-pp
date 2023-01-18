package ru.prostopodpis.api.dtos.accounts;

import lombok.Data;
import ru.prostopodpis.api.entity.User;

/**
 * author: Sergey Khaylov
 * email: <a href="mailto:info@proextlab.ru">info@proextlab.ru</a>
 * org: ProextLab
 * site: <a href="https://proextlab.ru">ProextLab</a>
 * created: 15.09.2022
 **/

@Data
public class AccountResponse {
    private String email;
    private String phone;
    private Float balance = 0f;
    private String orgName;
    private String timezone;
    private String defaultSender;
    private String currentTariff;

    public static AccountResponse of(User user) {
        AccountResponse resp = new AccountResponse();
        resp.setEmail(user.getEmail());
        resp.setPhone(user.getPhone());
        resp.setOrgName(user.getOrgName());
        resp.setTimezone(user.getTimezone());
        resp.setBalance(user.getBalance());
        resp.setDefaultSender(user.getDefaultSender());
        resp.setCurrentTariff(user.getCurrentTariff());
        return resp;
    }
}
