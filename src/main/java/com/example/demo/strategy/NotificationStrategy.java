package com.example.demo.strategy;

import com.example.demo.domain.enums.Channel;
import com.example.demo.domain.model.User;

public interface NotificationStrategy {

    Channel getChannel();

    void send(User user, String message);
}
