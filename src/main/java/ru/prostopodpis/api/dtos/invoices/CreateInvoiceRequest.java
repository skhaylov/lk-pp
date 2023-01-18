package ru.prostopodpis.api.dtos.invoices;

import lombok.Data;

/**
 * author: Sergey Khaylov
 * email: <a href="mailto:info@proextlab.ru">info@proextlab.ru</a>
 * org: ProextLab
 * site: <a href="https://proextlab.ru">ProextLab</a>
 * created: 30.09.2022
 **/

@Data
public class CreateInvoiceRequest {
    private Float amount;
}
