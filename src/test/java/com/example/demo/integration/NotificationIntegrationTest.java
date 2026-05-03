package com.example.demo.integration;

import com.example.demo.domain.Category;
import com.example.demo.domain.Message;
import com.example.demo.repository.NotificationLogRepository;
import com.example.demo.service.NotificationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
class NotificationIntegrationTest {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private NotificationLogRepository logRepository;

    @Test
    void shouldProcessNotificationAndStoreLogs() {

        logRepository.deleteAll();

        Message message = new Message(
                Category.SPORTS,
                "Integration test message"
        );

        notificationService.processMessage(message);

        assertFalse(logRepository.findAll().isEmpty());
    }
}