package ru.prostopodpis.api.dtos.projects;

import lombok.Data;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * author: Sergey Khaylov
 * email: <a href="mailto:info@proextlab.ru">info@proextlab.ru</a>
 * org: ProextLab
 * site: <a href="https://proextlab.ru">ProextLab</a>
 * created: 12.09.2022
 **/

@Data
public class CreateProjectRequest {
    @NotEmpty
    private String name;
    private String description;
    @NotEmpty
    private String callbackUrl;
    @NotEmpty
    private String apiKey;
    @Nullable
    private String sender;
}
