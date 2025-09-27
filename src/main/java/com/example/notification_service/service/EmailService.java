package com.example.notification_service.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailService {

    public void sendEmail(String email, String operation){
        String message;
        if ("CREATE".equalsIgnoreCase(operation)) {
            message = "Здравствуйте! Ваш аккаунт был успешно создан.";
        } else if ("DELETE".equalsIgnoreCase(operation)) {
            message = "Здравствуйте! Ваш аккаунт был удалён.";
        } else {
            throw new IllegalArgumentException("Неизвестная операция: " + operation);
        }

        log.info("Отправка email на {}: {}", email, message);
    }

}
