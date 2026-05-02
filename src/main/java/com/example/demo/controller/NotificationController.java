package com.example.demo.controller;

import com.example.demo.domain.Message;
import com.example.demo.domain.NotificationLog;
import com.example.demo.repository.NotificationLogRepository;
import com.example.demo.service.NotificationService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationService notificationService;
    private final NotificationLogRepository logRepository;

    public NotificationController(NotificationService notificationService,
                                  NotificationLogRepository logRepository) {
        this.notificationService = notificationService;
        this.logRepository = logRepository;
    }

    /**
     * Receives a message and triggers notification processing.
     */
    @PostMapping
    public String sendNotification(@Valid @RequestBody Message message) {
        notificationService.processMessage(message);
        return "Notification processed successfully";
    }

    /**
     * Returns all notification logs sorted from newest to oldest.
     */
    @GetMapping("/logs")
    public List<NotificationLog> getLogs() {
        return logRepository.findAllByOrderByTimestampDesc();
    }
}
