package com.example.demo.strategy;

import com.example.demo.domain.User;

public interface NotificationStrategy {
    void send(User user, String message);
}
