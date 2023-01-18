package ru.prostopodpis.api.dtos.requsites;

import lombok.Data;
import reactor.core.publisher.Mono;
import ru.prostopodpis.api.entity.Requsite;

/**
 * author: Sergey Khaylov
 * email: <a href="mailto:info@proextlab.ru">info@proextlab.ru</a>
 * org: ProextLab
 * site: <a href="https://proextlab.ru">ProextLab</a>
 * created: 11.09.2022
 **/


@Data
public class RequsiteResponseDto {
    private String id;

    private String orgName;
    private String address;
    private String postAddress;
    private String inn;
    private String kpp;
    private String ogrn;
    private String rs;
    private String bankName;
    private String bik;
    private String ks;
    private String director;
    private String osnovanie;
    private String phone;
    private String buhEmail;
    private String actEmail;
    private String additional;
    public static RequsiteResponseDto of(Requsite requsite) {
        RequsiteResponseDto dto = new RequsiteResponseDto();
        dto.setId(requsite.getId());
        dto.setOrgName(requsite.getOrgName());
        dto.setAddress(requsite.getAddress());
        dto.setPostAddress(requsite.getPostAddress());
        dto.setInn(requsite.getInn());
        dto.setKpp(requsite.getKpp());
        dto.setOgrn(requsite.getOgrn());
        dto.setRs(requsite.getRs());
        dto.setBankName(requsite.getBankName());
        dto.setBik(requsite.getBik());
        dto.setKs(requsite.getKs());
        dto.setDirector(requsite.getDirector());
        dto.setOsnovanie(requsite.getOsnovanie());
        dto.setPhone(requsite.getPhone());
        dto.setBuhEmail(requsite.getBuhEmail());
        dto.setActEmail(requsite.getActEmail());
        dto.setAdditional(requsite.getAdditional());

        return dto;
    }
}
