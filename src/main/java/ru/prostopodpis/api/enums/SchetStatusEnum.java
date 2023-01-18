package ru.prostopodpis.api.enums;

import lombok.Getter;

/**
 * author: Sergey Khaylov
 * email: <a href="mailto:info@proextlab.ru">info@proextlab.ru</a>
 * org: ProextLab
 * site: <a href="https://proextlab.ru">ProextLab</a>
 * created: 06.10.2022
 **/

public enum SchetStatusEnum {
    NEW("new"),
    PAYED("payed");

    @Getter
    private final String value;

    SchetStatusEnum(String value) {
        this.value = value;
    }
}
