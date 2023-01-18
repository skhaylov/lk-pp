package ru.prostopodpis.api.dtos.requsites;

import lombok.Data;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotEmpty;

/**
 * author: Sergey Khaylov
 * email: <a href="mailto:info@proextlab.ru">info@proextlab.ru</a>
 * org: ProextLab
 * site: <a href="https://proextlab.ru">ProextLab</a>
 * created: 12.09.2022
 **/

@Data
public class RequsiteUpdateDto {
    @NotEmpty
    private String orgName;
    @NotEmpty
    private String address;
    @NotEmpty
    private String postAddress;
    @NotEmpty
    private String inn;
    @NotEmpty
    private String kpp;
    @NotEmpty
    private String ogrn;
    @NotEmpty
    private String rs;
    @NotEmpty
    private String bankName;
    @NotEmpty
    private String bik;
    @NotEmpty
    private String ks;
    @NotEmpty
    private String director;
    @NotEmpty
    private String osnovanie;
    @NotEmpty
    private String phone;
    @NotEmpty
    private String buhEmail;
    @NotEmpty
    private String actEmail;
    @Nullable
    private String additional;
}
