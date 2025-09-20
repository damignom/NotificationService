package com.example.notification_service.service;

import org.springframework.stereotype.Service;

@Service
public class EmailService {

    public void sendEmail(String email, String message){
        System.out.println("Отправка email: " + email + " | " + message);
    }

}
