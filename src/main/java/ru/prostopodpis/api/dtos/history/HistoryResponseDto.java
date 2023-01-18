package ru.prostopodpis.api.dtos.history;

import lombok.Data;
import ru.prostopodpis.api.entity.History;
import ru.prostopodpis.api.entity.Invoice;
import ru.prostopodpis.api.entity.ProjectRecipient;

import java.util.Date;

/**
 * author: Sergey Khaylov
 * email: <a href="mailto:info@proextlab.ru">info@proextlab.ru</a>
 * org: ProextLab
 * site: <a href="https://proextlab.ru">ProextLab</a>
 * created: 18.09.2022
 **/

@Data
public class HistoryResponseDto {
    private String id;
    private Date sentDate;
    private String status;
    private String message;
    private String recipient;
    private Boolean isInvoice = false;
    private Float amount;

    public static HistoryResponseDto of(History history) {
        HistoryResponseDto resp = new HistoryResponseDto();
        resp.setId(history.getId());
        resp.setSentDate(history.getSentDate());
        resp.setStatus(history.getStatus());
        resp.setMessage(history.getMessage());
        resp.setAmount(0.3f);
        resp.setRecipient(history.getRecipient());
        return resp;
    }

    public static HistoryResponseDto of(Invoice invoice) {
        HistoryResponseDto resp = new HistoryResponseDto();
        resp.setId(invoice.getId());
        resp.setSentDate(Date.from(invoice.getCreatedDate()));
        resp.setStatus(invoice.getStatus().getValue());
        resp.setMessage("Пополнение");
        resp.setIsInvoice(true);
        resp.setAmount(invoice.getAmount());
        return resp;
    }
}
