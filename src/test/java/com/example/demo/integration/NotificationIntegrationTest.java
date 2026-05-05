package com.example.demo.integration;

import com.example.demo.config.TestAsyncConfig;
import com.example.demo.domain.enums.Category;
import com.example.demo.domain.model.Message;
import com.example.demo.domain.model.NotificationLog;
import com.example.demo.repository.NotificationLogRepository;
import com.example.demo.service.NotificationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@Import(TestAsyncConfig.class)
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

        var logs = logRepository.findAll();

        assertFalse(logs.isEmpty());

        NotificationLog log = logs.get(0);

        assertEquals(Category.SPORTS, log.getCategory());
        assertEquals("Integration test message", log.getMessage());
        assertEquals("SUCCESS", log.getStatus());
    }
}