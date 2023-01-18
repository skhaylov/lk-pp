package ru.prostopodpis.api.dtos.appeals;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * author: Sergey Khaylov
 * email: <a href="mailto:info@proextlab.ru">info@proextlab.ru</a>
 * org: ProextLab
 * site: <a href="https://proextlab.ru">ProextLab</a>
 * created: 05.10.2022
 **/

@Data
public class CreateAppealDto {
    @NotBlank
    private String typeId;
    @NotBlank
    private String message;
}
