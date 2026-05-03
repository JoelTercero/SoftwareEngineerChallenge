package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(
        name = "notification_logs",
        indexes = {
                @Index(name = "idx_timestamp", columnList = "timestamp")
        }
)
public class NotificationLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Enumerated(EnumType.STRING)
    private Channel channel;

    @Column(length = 500)
    private String message;

    @Column(length = 50)
    private String status;

    @Column(length = 255)
    private String error;

    @Column
    private LocalDateTime timestamp;

}