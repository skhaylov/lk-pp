package ru.prostopodpis.api.services;

import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Mono;
import ru.prostopodpis.api.entity.User;

import java.net.http.HttpResponse;

/**
 * author: Sergey Khaylov
 * email: <a href="mailto:info@proextlab.ru">info@proextlab.ru</a>
 * org: ProextLab
 * site: <a href="https://proextlab.ru">ProextLab</a>
 * created: 04.10.2022
 **/

public interface UploadService {
    Mono<String> uploadFile(String subdir, Mono<FilePart> dogovorMono);
}
