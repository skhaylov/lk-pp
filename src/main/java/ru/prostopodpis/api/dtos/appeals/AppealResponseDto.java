package ru.prostopodpis.api.dtos.appeals;

import lombok.Data;
import ru.prostopodpis.api.entity.Appeal;

import java.util.Date;

/**
 * author: Sergey Khaylov
 * email: <a href="mailto:info@proextlab.ru">info@proextlab.ru</a>
 * org: ProextLab
 * site: <a href="https://proextlab.ru">ProextLab</a>
 * created: 05.10.2022
 **/

@Data
public class AppealResponseDto {
    private String id;
    private String typeId;
    private String message;
    private Date created;

    public static AppealResponseDto of(Appeal appeal) {
        AppealResponseDto resp = new AppealResponseDto();
        resp.setId(appeal.getId());
        resp.setTypeId(appeal.getTypeId());
        resp.setMessage(appeal.getMessage());
        resp.setCreated(appeal.getCreatedDate());
        return resp;
    }
}
