package ru.prostopodpis.api.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.prostopodpis.api.entity.Sender;

import java.util.HashMap;

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
public class TelegramBotMessageSenderChannelServiceImpl implements MessageSenderChannelService {

    public static String TOPIC_PREFIX = "pp_";
    private final KafkaTemplate<String, String> kafkaTemplate;

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public boolean applicableForSender(Sender sender) {
        log.info(sender);
        return sender.getTelegramToken() != null && !sender.getTelegramToken().isBlank();
    }

    @Override
    public Mono<Void> send(String senderId, String recipientId, String userId, String message) {
        log.info("here");
        return Mono.just(kafkaTemplate.send(getTopic(senderId), prepareMessage(recipientId, userId, message))).then();
    }

    private String getTopic(String id) {
        return TOPIC_PREFIX + id;
    }

    private String prepareMessage(String recipientId, String userId, String message) {
        try {
            return mapper.writeValueAsString(new HashMap<String, String>(){{
                put("recipientId", recipientId);
                put("userId", userId);
                put("message", message);
            }});
        } catch (JsonProcessingException e) {
            log.error("Fail on prepare message: {}", e.getMessage());
        }
        return null;
    }
}
