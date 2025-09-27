package com.example.notification_service.controller;

import com.example.notification_service.service.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class NotificationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EmailService emailService;

    @Test
    void testSendCreateEmail() throws Exception {
        mockMvc.perform(post("/api/notifications/send")
                        .param("email", "roma@gmail.com")
                        .param("operation", "CREATE"))
                .andExpect(status().isOk());

        verify(emailService).sendEmail("roma@gmail.com",
                "CREATE");
    }

    @Test
    void testSendDeleteEmail() throws Exception {
        mockMvc.perform(post("/api/notifications/send")
                .param("email", "roma@gmail.com")
                .param("operation", "DELETE"))
                .andExpect(status().isOk());

        verify(emailService).sendEmail("roma@gmail.com",
                "DELETE");
    }
}
