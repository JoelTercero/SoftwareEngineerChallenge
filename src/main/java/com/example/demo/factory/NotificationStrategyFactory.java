package com.example.demo.factory;

import com.example.demo.domain.Channel;
import com.example.demo.strategy.NotificationStrategy;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class NotificationStrategyFactory {

    private final Map<String, NotificationStrategy> strategies;

    public NotificationStrategyFactory(Map<String, NotificationStrategy> strategies) {
        this.strategies = strategies;
    }

    public NotificationStrategy getStrategy(Channel channel) {
        return switch (channel) {
            case EMAIL -> strategies.get("emailNotificationStrategy");
            case SMS -> strategies.get("smsNotificationStrategy");
            case PUSH -> strategies.get("pushNotificationStrategy");
        };
    }
}
