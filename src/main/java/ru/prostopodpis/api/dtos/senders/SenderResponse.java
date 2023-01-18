package ru.prostopodpis.api.dtos.senders;

import lombok.Data;
import org.reactivestreams.Publisher;
import ru.prostopodpis.api.entity.Sender;

/**
 * author: Sergey Khaylov
 * email: <a href="mailto:info@proextlab.ru">info@proextlab.ru</a>
 * org: ProextLab
 * site: <a href="https://proextlab.ru">ProextLab</a>
 * created: 13.09.2022
 **/

@Data
public class SenderResponse {
    private String id;
    private String name;
    private Boolean internal = false;
    private Boolean active = false;

    public static SenderResponse of(Sender sender) {
        SenderResponse resp = new SenderResponse();
        resp.setId(sender.getId());
        resp.setName(sender.getName());
        if (sender.getUserId() == null){
            resp.setInternal(true);
        }
        resp.setActive(sender.getActive());
        return resp;
    }
}
