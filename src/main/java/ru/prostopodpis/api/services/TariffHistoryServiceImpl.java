package ru.prostopodpis.api.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.prostopodpis.api.entity.Tariff;
import ru.prostopodpis.api.entity.TariffHistory;
import ru.prostopodpis.api.entity.User;
import ru.prostopodpis.api.repositories.ReactiveTariffHistoryRepository;

/**
 * author: Sergey Khaylov
 * email: <a href="mailto:info@proextlab.ru">info@proextlab.ru</a>
 * org: ProextLab
 * site: <a href="https://proextlab.ru">ProextLab</a>
 * created: 15.09.2022
 **/

@Service
@RequiredArgsConstructor
public class TariffHistoryServiceImpl implements TariffHistoryService {

    private final ReactiveTariffHistoryRepository repository;

    @Override
    public Mono<Void> changeTariff(User user, Tariff tariff) {
        return this.changeTariff(user.getId(), tariff.getId());
    }

    @Override
    public Mono<Void> changeTariff(String userId, String tariffId) {
        TariffHistory tariffHistory = new TariffHistory();
        tariffHistory.setUserId(userId);
        tariffHistory.setTariffId(tariffId);
        return repository.save(tariffHistory).then();
    }
}
