package ru.prostopodpis.api.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.prostopodpis.api.dtos.requsites.RequsiteUpdateDto;
import ru.prostopodpis.api.entity.Requsite;
import ru.prostopodpis.api.entity.User;
import ru.prostopodpis.api.repositories.ReactiveRequsiteRepository;

/**
 * author: Sergey Khaylov
 * email: <a href="mailto:info@proextlab.ru">info@proextlab.ru</a>
 * org: ProextLab
 * site: <a href="https://proextlab.ru">ProextLab</a>
 * created: 11.09.2022
 **/

@Service
@RequiredArgsConstructor
public class RequsitesServiceImpl implements RequsitesService {
    private final ReactiveRequsiteRepository repository;

    @Override
    public Mono<Requsite> findByUser(User user) {
        return repository.findByUserId(user.getId());
    }

    @Override
    public Mono<Requsite> update(User user, RequsiteUpdateDto requsiteUpdateDto) {
        return repository.findByUserId(user.getId())
                .switchIfEmpty(Mono.just(new Requsite()))
                .flatMap(requsite -> {
                    requsite.setUserId(user.getId());
                    requsite.setOrgName(requsiteUpdateDto.getOrgName());
                    requsite.setAddress(requsiteUpdateDto.getAddress());
                    requsite.setPostAddress(requsiteUpdateDto.getPostAddress());
                    requsite.setInn(requsiteUpdateDto.getInn());
                    requsite.setKpp(requsiteUpdateDto.getKpp());
                    requsite.setOgrn(requsiteUpdateDto.getOgrn());
                    requsite.setRs(requsiteUpdateDto.getRs());
                    requsite.setBankName(requsiteUpdateDto.getBankName());
                    requsite.setBik(requsiteUpdateDto.getBik());
                    requsite.setKs(requsiteUpdateDto.getKs());
                    requsite.setDirector(requsiteUpdateDto.getDirector());
                    requsite.setOsnovanie(requsiteUpdateDto.getOsnovanie());
                    requsite.setPhone(requsiteUpdateDto.getPhone());
                    requsite.setBuhEmail(requsiteUpdateDto.getBuhEmail());
                    requsite.setActEmail(requsiteUpdateDto.getActEmail());
                    requsite.setAdditional(requsiteUpdateDto.getAdditional());

                    return repository.save(requsite);
                });
    }

    @Override
    public Flux<Requsite> findAll() {
        return repository.findAll();
    }
}
