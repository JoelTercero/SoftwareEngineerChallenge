package com.example.demo.controller;

import com.example.demo.domain.Category;
import com.example.demo.domain.Message;
import com.example.demo.domain.NotificationLog;
import com.example.demo.dto.MessageRequest;
import com.example.demo.repository.NotificationLogRepository;
import com.example.demo.service.NotificationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> send(@Valid @RequestBody MessageRequest request) {

        Category category = Category.valueOf(request.getCategory().toUpperCase());

        Message message = new Message(category, request.getContent());

        notificationService.processMessage(message);

        return ResponseEntity.ok("Notification processed successfully");
    }

    /**
     * Returns all notification logs sorted from newest to oldest.
     */
    @GetMapping("/logs")
    public List<NotificationLog> getLogs() {
        return logRepository.findAllByOrderByTimestampDesc();
    }
}
