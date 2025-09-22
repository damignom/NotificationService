package com.example.notification_service.listener;

import com.example.notification_service.service.EmailService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationListener {

    private final EmailService emailService;

    public NotificationListener(EmailService emailService) {
        this.emailService = emailService;
    }

    @KafkaListener(topics = "user-event", groupId = "notification-group")
    public void listen(String message) {
        // Сообщение формата "CREATE:user@example.com" или "DELETE:user@example.com"
        String[] parts = message.split(":");
        String operation = parts[0];
        String email = parts[1];

        if ("CREATE".equals(operation)) {
            emailService.sendEmail(email, "Здравствуйте! Ваш аккаунт был успешно создан.");
        } else if ("DELETE".equals(operation)) {
            emailService.sendEmail(email, "Здравствуйте! Ваш аккаунт был удалён.");
        }
    }
}
