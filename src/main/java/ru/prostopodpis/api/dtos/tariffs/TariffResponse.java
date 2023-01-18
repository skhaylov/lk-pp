package ru.prostopodpis.api.dtos.tariffs;

import lombok.Data;
import org.reactivestreams.Publisher;
import ru.prostopodpis.api.entity.Tariff;

/**
 * author: Sergey Khaylov
 * email: <a href="mailto:info@proextlab.ru">info@proextlab.ru</a>
 * org: ProextLab
 * site: <a href="https://proextlab.ru">ProextLab</a>
 * created: 15.09.2022
 **/

@Data
public class TariffResponse {
    private String id;
    private String name;
    private String description;

    public static TariffResponse of(Tariff tariff) {
        TariffResponse resp = new TariffResponse();
        resp.setId(tariff.getId());
        resp.setName(tariff.getName());
        resp.setDescription(tariff.getDescription());
        return resp;
    }
}
