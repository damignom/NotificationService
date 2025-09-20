package com.example.notification_service.listener;

import com.example.notification_service.service.EmailService;
import org.springframework.stereotype.Service;

@Service
public class NotificationListener {

    private final EmailService emailService;

    public NotificationListener(EmailService emailService) {
        this.emailService = emailService;
    }

    public void listen(String message) {
        // Сообщение формата "CREATE:user@example.com" или "DELETE:user@example.com"
        String[] parts = message.split(":");
        String operation = parts[0];
        String email = parts[1];

        if ("CREATE".equals(operation)) {
            emailService.sendEmail(email, "Ваш аккаунт был создан.");
        } else if ("DELETE".equals(operation)) {
            emailService.sendEmail(email, "Ваш аккаунт был удалён.");
        }
    }
}
