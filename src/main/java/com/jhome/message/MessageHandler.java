package com.jhome.message;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageHandler {

    private final MessageRepository messageRepository;

    @Bean
    public Consumer<Message<String>> handleMessage() {
        return message -> {
            String messagePayload = message.getPayload();
            MessageEntity messageEntity = MessageEntity.builder()
                    .message(messagePayload)
                    .build();
            log.info("Received message: {}", messagePayload);
            messageRepository.save(messageEntity);
        };
    }
}
