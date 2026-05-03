package com.example.demo.factory;

import com.example.demo.domain.Channel;
import com.example.demo.strategy.NotificationStrategy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class NotificationStrategyFactory {

    private final Map<Channel, NotificationStrategy> strategies;

    public NotificationStrategyFactory(List<NotificationStrategy> strategyList) {
        this.strategies = strategyList.stream()
                .collect(Collectors.toMap(
                        NotificationStrategy::getChannel,
                        strategy -> strategy
                ));
    }

    public NotificationStrategy getStrategy(Channel channel) {
        return strategies.get(channel);
    }
}
