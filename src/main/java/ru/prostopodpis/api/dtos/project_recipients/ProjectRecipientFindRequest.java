package ru.prostopodpis.api.dtos.project_recipients;

import lombok.Data;

/**
 * author: Sergey Khaylov
 * email: <a href="mailto:info@proextlab.ru">info@proextlab.ru</a>
 * org: ProextLab
 * site: <a href="https://proextlab.ru">ProextLab</a>
 * created: 17.09.2022
 **/

@Data
public class ProjectRecipientFindRequest {
    private String projectId;
    private String email;
    private String phone;
}
