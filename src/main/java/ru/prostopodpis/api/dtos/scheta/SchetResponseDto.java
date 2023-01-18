package ru.prostopodpis.api.dtos.scheta;

import lombok.Data;
import ru.prostopodpis.api.entity.Schet;

import java.util.Date;

/**
 * author: Sergey Khaylov
 * email: <a href="mailto:info@proextlab.ru">info@proextlab.ru</a>
 * org: ProextLab
 * site: <a href="https://proextlab.ru">ProextLab</a>
 * created: 06.10.2022
 **/

@Data
public class SchetResponseDto {
    private String id;
    private String userId;
    private String number;
    private Float amount;
    private Date created;
    private Date lastModified;
    private String status;

    public static SchetResponseDto of(Schet schet) {
        SchetResponseDto resp = new SchetResponseDto();
        resp.setId(schet.getId());
        resp.setAmount(schet.getAmount());
        resp.setNumber(schet.getNumber());
        resp.setCreated(schet.getCreatedDate());
        resp.setLastModified(schet.getLastModifiedDate());
        resp.setUserId(schet.getUserId());
        resp.setStatus(schet.getStatus().getValue());
        return resp;
    }
}
