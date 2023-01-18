package ru.prostopodpis.api.dtos.timezones;

import lombok.Data;
import ru.prostopodpis.api.entity.TimeZone;

/**
 * author: Sergey Khaylov
 * email: <a href="mailto:info@proextlab.ru">info@proextlab.ru</a>
 * org: ProextLab
 * site: <a href="https://proextlab.ru">ProextLab</a>
 * created: 05.10.2022
 **/

@Data
public class TimeZoneResponseDto {
    private String id;
    private String name;
    private Integer gmtDiff;

    public static TimeZoneResponseDto of(TimeZone timeZone) {
        TimeZoneResponseDto resp = new TimeZoneResponseDto();
        resp.setId(timeZone.getId());
        resp.setName(timeZone.getName());
        resp.setGmtDiff(timeZone.getGmtDiff());
        return resp;
    }
}
