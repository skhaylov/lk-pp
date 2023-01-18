package ru.prostopodpis.api.dtos.tariffs;

import lombok.Data;

/**
 * author: Sergey Khaylov
 * email: <a href="mailto:info@proextlab.ru">info@proextlab.ru</a>
 * org: ProextLab
 * site: <a href="https://proextlab.ru">ProextLab</a>
 * created: 15.09.2022
 **/

@Data
public class ActivateTariffRequest {
    private String tariffId;
}
