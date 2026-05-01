package com.example.demo.service;

import com.example.demo.domain.Channel;
import com.example.demo.domain.Message;
import com.example.demo.domain.User;
import com.example.demo.factory.NotificationStrategyFactory;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final UserRepository userRepository;
    private final NotificationStrategyFactory factory;

    public NotificationService(UserRepository userRepository,
                               NotificationStrategyFactory factory) {
        this.userRepository = userRepository;
        this.factory = factory;
    }

    public void processMessage(Message message) {

        var users = userRepository.getUsers();

        for (User user : users) {

            if (!user.isSubscribedTo(message.getCategory())) {
                continue;
            }

            for (Channel channel : user.getChannels()) {
                try {
                    var strategy = factory.getStrategy(channel);
                    strategy.send(user, message.getContent());

                    System.out.println("SUCCESS");
                } catch (Exception e) {
                    System.out.println("FAILED: " + e.getMessage());
                }
            }
        }
    }
}