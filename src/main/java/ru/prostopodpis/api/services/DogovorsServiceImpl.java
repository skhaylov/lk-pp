package ru.prostopodpis.api.services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.prostopodpis.api.entity.Dogovor;
import ru.prostopodpis.api.entity.User;
import ru.prostopodpis.api.repositories.ReactiveDogovorRepository;

import java.util.Comparator;

/**
 * author: Sergey Khaylov
 * email: <a href="mailto:info@proextlab.ru">info@proextlab.ru</a>
 * org: ProextLab
 * site: <a href="https://proextlab.ru">ProextLab</a>
 * created: 04.10.2022
 **/

@Service
@RequiredArgsConstructor
public class DogovorsServiceImpl implements DogovorsService {
    private final UploadService uploadService;
    private final ReactiveDogovorRepository reactiveDogovorRepository;

    @Override
    public Mono<Dogovor> uploadDogovor(User user, Mono<FilePart> dogovorMono) {
        return uploadService
                .uploadFile(user.getId() + "/dogovors", dogovorMono)
                .flatMap(filename -> createDogovor(user, filename));
    }

    private Mono<Dogovor> createDogovor(User user, String uploadedDogovorFilename) {
        Dogovor dogovor = new Dogovor();
        dogovor.setUserId(user.getId());
        dogovor.setFilename(uploadedDogovorFilename);
        return reactiveDogovorRepository.save(dogovor);
    }

    @Override
    public Flux<Dogovor> findByUser(User user) {
        return reactiveDogovorRepository
                .findByUserId(user.getId())
                .sort((d1, d2) -> {
                    if (d1.getCreatedDate().isAfter(d2.getCreatedDate())) {
                        return 1;
                    }
                    if (d1.getCreatedDate().isBefore(d2.getCreatedDate())) {
                        return -1;
                    }
                    return 0;
                });
    }

    @Override
    public Mono<Dogovor> findByUserAndId(User user, String id) {
        return reactiveDogovorRepository.findByUserIdAndId(user.getId(), id);
    }
}
