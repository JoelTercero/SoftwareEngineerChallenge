package com.example.demo.controller;

import com.example.demo.domain.Message;
import com.example.demo.service.NotificationService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationService service;

    public NotificationController(NotificationService service) {
        this.service = service;
    }

    @PostMapping
    public String send(@Valid @RequestBody Message message) {
        service.processMessage(message);
        return "Processed";
    }
}
