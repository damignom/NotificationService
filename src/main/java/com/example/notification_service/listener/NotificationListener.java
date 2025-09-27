package com.example.notification_service.listener;

import com.example.notification_service.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationListener {

    private final EmailService emailService;

    @KafkaListener(topics = "user-event", groupId = "notification-group")
    public void listen(String message) {
        log.info("Получено сообщение из Kafka: {}", message);

        String[] parts = message.split(":");
        if (parts.length != 2) {
            log.error("Неверный формат сообщения: {}", message);
            return;
        }

        String operation = parts[0].trim();
        String email = parts[1].trim();

        try {
            emailService.sendEmail(email, operation);
        } catch (IllegalArgumentException e) {
            log.error("Ошибка обработки сообщения: {}", e.getMessage());
        }
    }
}
