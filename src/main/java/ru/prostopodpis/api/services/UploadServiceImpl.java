package ru.prostopodpis.api.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.prostopodpis.api.entity.User;

import java.io.File;
import java.nio.file.Path;

/**
 * author: Sergey Khaylov
 * email: <a href="mailto:info@proextlab.ru">info@proextlab.ru</a>
 * org: ProextLab
 * site: <a href="https://proextlab.ru">ProextLab</a>
 * created: 04.10.2022
 **/

@Service
@RequiredArgsConstructor
public class UploadServiceImpl implements UploadService {
    @Value("${upload-dir}")
    private String UPLOAD_DIR;

    @Override
    public Mono<String> uploadFile(String subdir, Mono<FilePart> dogovorMono) {
        return dogovorMono
                .flatMap(filePart -> {
                    Path destPath = Path.of(UPLOAD_DIR, subdir, filePart.filename());
                    File subdirPath = Path.of(UPLOAD_DIR, subdir).toFile();
                    if (!subdirPath.exists()) {
                        subdirPath.mkdirs();
                    }
                    filePart.transferTo(destPath).subscribe();
                    return Mono.just(destPath.toString());
                });
    }
}
