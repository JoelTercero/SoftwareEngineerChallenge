package com.example.demo.strategy;

import com.example.demo.domain.User;
import org.springframework.stereotype.Component;

@Component
public class PushNotificationStrategy implements NotificationStrategy {

    @Override
    public void send(User user, String message) {
        System.out.println("Sending PUSH to " + user.getName());
    }
}
