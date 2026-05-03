package com.example.demo.dto;

import lombok.Data;

@Data
public class NotificationLogResponse {

    private Long userId;
    private String category;
    private String channel;
    private String message;
    private String status;
    private String timestamp;

    public NotificationLogResponse(Long userId, String category, String channel,
                                   String message, String status, String timestamp) {
        this.userId = userId;
        this.category = category;
        this.channel = channel;
        this.message = message;
        this.status = status;
        this.timestamp = timestamp;
    }
}
