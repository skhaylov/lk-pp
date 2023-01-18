package ru.prostopodpis.api.dtos.projects;

import lombok.Data;
import ru.prostopodpis.api.entity.Project;

/**
 * author: Sergey Khaylov
 * email: <a href="mailto:info@proextlab.ru">info@proextlab.ru</a>
 * org: ProextLab
 * site: <a href="https://proextlab.ru">ProextLab</a>
 * created: 12.09.2022
 **/

@Data
public class ProjectResponse {
    private String id;
    private String name;
    private String description;
    private String apiKey;
    private String callbackUrl;
    private String sender;

    public static ProjectResponse of(Project project) {
        ProjectResponse resp = new ProjectResponse();
        resp.setId(project.getId());
        resp.setName(project.getName());
        resp.setDescription(project.getDescription());
        resp.setApiKey(project.getApiKey());
        resp.setCallbackUrl(project.getCallbackUrl());
        resp.setSender(project.getSender());
        return resp;
    }
}
