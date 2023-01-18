package ru.prostopodpis.api.dtos.invoices;

import lombok.Data;
import ru.prostopodpis.api.entity.Invoice;

/**
 * author: Sergey Khaylov
 * email: <a href="mailto:info@proextlab.ru">info@proextlab.ru</a>
 * org: ProextLab
 * site: <a href="https://proextlab.ru">ProextLab</a>
 * created: 30.09.2022
 **/

@Data
public class InvoiceCreateResponse {
    private String invoiceId;

    public static InvoiceCreateResponse of(Invoice invoice) {
        InvoiceCreateResponse resp = new InvoiceCreateResponse();
        resp.setInvoiceId(invoice.getId());
        return resp;
    }
}
