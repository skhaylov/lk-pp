package ru.prostopodpis.api.controllers;

import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.prostopodpis.api.dtos.dogovors.DogovorResponse;
import ru.prostopodpis.api.services.DogovorsService;
import ru.prostopodpis.api.services.UsersService;

import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;

/**
 * author: Sergey Khaylov
 * email: <a href="mailto:info@proextlab.ru">info@proextlab.ru</a>
 * org: ProextLab
 * site: <a href="https://proextlab.ru">ProextLab</a>
 * created: 04.10.2022
 **/

@RestController
@RequestMapping("/api/v1/dogovors")
@RequiredArgsConstructor
public class DogovorsController {

    private final UsersService usersService;

    private final DogovorsService dogovorsService;

    @PostMapping
    public Mono<ResponseEntity<DogovorResponse>> uploadDogovor(Mono<Principal> principalMono,
                                                               @RequestPart(name = "dogovor") Mono<FilePart> dogovorMono) {
        return principalMono
                .flatMap(principal -> usersService.findByEmailOrPhone(principal.getName()))
                .flatMap(user -> dogovorsService.uploadDogovor(user, dogovorMono))
                .map(dogovor -> ResponseEntity.status(HttpStatus.CREATED).body(DogovorResponse.of(dogovor)))
                .defaultIfEmpty(ResponseEntity.internalServerError().build());
    }

    @GetMapping
    public Flux<DogovorResponse> findByUser(Mono<Principal> principalMono) {
        return principalMono
                .flatMap(principal -> usersService.findByEmailOrPhone(principal.getName()))
                .flatMapMany(dogovorsService::findByUser)
                .map(DogovorResponse::of);
    }

//    @GetMapping(value = "/download/{id}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
//    @ResponseBody
//    @ResponseStatus(HttpStatus.OK)
//    public Mono<byte[]> downloadDogovor(Mono<Principal> principalMono,
//                                                   @PathVariable String id) {
//        return principalMono
//                .flatMap(principal -> usersService.findByEmailOrPhone(principal.getName()))
//                .flatMap(user -> dogovorsService.findByUserAndId(user, id))
//                .flatMap(dogovor -> {
//                    InputStream in = getClass()
//                            .getResourceAsStream(dogovor.getFilename());
//                    try {
//                        return IOUtils.toByteArray(in);
//                    } catch (IOException e) {
//                        return IOUtils.toByteArray(null);
//                    }
//                });
//    }
}
