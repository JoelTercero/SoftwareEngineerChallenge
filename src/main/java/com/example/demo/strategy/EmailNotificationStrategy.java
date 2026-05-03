package com.example.demo.strategy;

import com.example.demo.domain.Channel;
import com.example.demo.domain.User;
import org.springframework.stereotype.Component;

@Component
public class EmailNotificationStrategy implements NotificationStrategy {

    @Override
    public Channel getChannel() {
        return Channel.EMAIL;
    }

    @Override
    public void send(User user, String message) {
        System.out.println("Sending EMAIL to " + user.getEmail());
    }
}
