package ru.prostopodpis.api.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

/**
 * author: Sergey Khaylov
 * email: <a href="mailto:info@proextlab.ru">info@proextlab.ru</a>
 * org: ProextLab
 * site: <a href="https://proextlab.ru">ProextLab</a>
 * created: 16.09.2022
 **/

@Service
@RequiredArgsConstructor
@Log4j2
public class MessagesServiceImpl implements MessagesService {
    private final SendersService sendersService;
    private final ProjectsService projectsService;

    private final List<MessageSenderChannelService> senderChannelServices;

    @Override
    public Mono<Void> send(String apiKey, String recipientId, String userId, String message) {
        return projectsService
                .findByApiKey(apiKey)
                .flatMap(project -> sendersService.findById(project.getSender()))
                .flatMapMany(sender -> {
                            if (sender.getActive()) {
                                senderChannelServices.stream()
                                        .filter(service -> service.applicableForSender(sender))
                                        .peek(log::info)
                                        .forEach(service -> service.send(sender.getId(), recipientId, userId, message).subscribe());
                            }
                            return Mono.empty();
                        }
                ).then();
    }

}
