package ru.prostopodpis.api.dtos.project_recipients;

import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * author: Sergey Khaylov
 * email: <a href="mailto:info@proextlab.ru">info@proextlab.ru</a>
 * org: ProextLab
 * site: <a href="https://proextlab.ru">ProextLab</a>
 * created: 17.09.2022
 **/

@Data
public class CreateProjectRecipientRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String phone;
    @NotBlank
    private String email;

    private String projectId;
    private String apiKey;
}
