package ru.prostopodpis.api.dtos.dogovors;

import lombok.Data;
import ru.prostopodpis.api.entity.Dogovor;

import java.util.Date;

/**
 * author: Sergey Khaylov
 * email: <a href="mailto:info@proextlab.ru">info@proextlab.ru</a>
 * org: ProextLab
 * site: <a href="https://proextlab.ru">ProextLab</a>
 * created: 04.10.2022
 **/

@Data
public class DogovorResponse {
    private String id;
    private String filename;
    private String number;
    private Date created;

    public static DogovorResponse of(Dogovor dogovor) {
        DogovorResponse resp = new DogovorResponse();
        resp.setId(dogovor.getId());
        resp.setFilename(dogovor.getFilename());
        resp.setCreated(Date.from(dogovor.getCreatedDate()));
        resp.setNumber(dogovor.getNumber());
        return resp;
    }
}
