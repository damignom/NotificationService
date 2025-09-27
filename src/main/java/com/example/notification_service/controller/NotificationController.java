package com.example.notification_service.controller;

import com.example.notification_service.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notifications/")
@RequiredArgsConstructor
@Slf4j
public class NotificationController {
    private final EmailService emailService;

    @PostMapping("/send")
    public ResponseEntity<String> sendEmail(
            @RequestParam String email,
            @RequestParam String operation) {

        try {
            emailService.sendEmail(email, operation);
            return ResponseEntity.ok("Сообщение отправлено на " + email);
        } catch (IllegalArgumentException e) {
            log.error("Ошибка: {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
