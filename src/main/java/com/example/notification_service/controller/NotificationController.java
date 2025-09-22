package com.example.notification_service.controller;

import com.example.notification_service.service.EmailService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notifications/")
public class NotificationController {
    private final EmailService emailService;

    public NotificationController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send")
    public String sendEmail(@RequestParam String email, @RequestParam String operation) {
        if ("CREATE".equalsIgnoreCase(operation)) {
            emailService.sendEmail(email, "Здравствуйте! Ваш аккаунт был успешно создан.");
        } else if ("DELETE".equalsIgnoreCase(operation)) {
            emailService.sendEmail(email, "Здравствуйте! Ваш аккаунт был удалён.");
        } else {
            return "Неизвестная операция";
        }
        return "Сообщение отправлено на " + email;
    }
}
