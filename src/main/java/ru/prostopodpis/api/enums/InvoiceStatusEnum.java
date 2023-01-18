package ru.prostopodpis.api.enums;

import lombok.Getter;

/**
 * author: Sergey Khaylov
 * email: <a href="mailto:info@proextlab.ru">info@proextlab.ru</a>
 * org: ProextLab
 * site: <a href="https://proextlab.ru">ProextLab</a>
 * created: 01.10.2022
 **/

public enum InvoiceStatusEnum {
    NEW("NEW"),
    DONE("DONE"),
    FAIL("FAIL");

    @Getter
    private final String value;

    InvoiceStatusEnum(String value) {
        this.value = value;
    }
}
