package com.example.demo.strategy;

import com.example.demo.domain.Channel;
import com.example.demo.domain.User;

public interface NotificationStrategy {

    Channel getChannel();

    void send(User user, String message);
}
