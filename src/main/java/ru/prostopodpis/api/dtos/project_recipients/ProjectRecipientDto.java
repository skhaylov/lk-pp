package ru.prostopodpis.api.dtos.project_recipients;

import lombok.Data;
import ru.prostopodpis.api.entity.ProjectRecipient;
import ru.prostopodpis.api.entity.Sender;

import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * author: Sergey Khaylov
 * email: <a href="mailto:info@proextlab.ru">info@proextlab.ru</a>
 * org: ProextLab
 * site: <a href="https://proextlab.ru">ProextLab</a>
 * created: 17.09.2022
 **/

@Data
public class ProjectRecipientDto {
    private String id;
    private String phone;
    private String email;
    private String name;
    private Date created;
    private String projectId;
    private Boolean active = false;
    private String joinUrl;

    public static ProjectRecipientDto of(ProjectRecipient projectRecipient) {
        ProjectRecipientDto resp = new ProjectRecipientDto();
        resp.setId(projectRecipient.getId());
        resp.setPhone(projectRecipient.getPhone());
        resp.setEmail(projectRecipient.getEmail());
        resp.setName(projectRecipient.getName());
        resp.setProjectId(projectRecipient.getProjectId());
        resp.setCreated(new Date(projectRecipient.getCreatedDate().toEpochMilli()));
        resp.setActive(projectRecipient.getTelegramChatId() != null && !projectRecipient.getTelegramChatId().isBlank());
        return resp;
    }

    public static ProjectRecipientDto of(ProjectRecipient projectRecipient, Sender sender) {
        ProjectRecipientDto resp = ProjectRecipientDto.of(projectRecipient);
        //todo use projectRecipientService joinUrl method
        resp.setJoinUrl(String.format("https://t.me/%s?start=%s", sender.getTelegramBotName(), projectRecipient.getId()));
        return resp;
    }
}
