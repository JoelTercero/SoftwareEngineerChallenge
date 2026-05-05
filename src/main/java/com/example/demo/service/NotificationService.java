package com.example.demo.service;

import com.example.demo.domain.enums.Channel;
import com.example.demo.domain.model.Message;
import com.example.demo.domain.model.NotificationLog;
import com.example.demo.domain.model.User;
import com.example.demo.factory.NotificationStrategyFactory;
import com.example.demo.repository.NotificationLogRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.strategy.NotificationStrategy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Processes a message and sends notifications to users subscribed
 * to the message category via their preferred channels.
 * Logs the result of each notification attempt.
 */
@Service
public class NotificationService {

    private final UserRepository userRepository;
    private final NotificationStrategyFactory factory;
    private final NotificationLogRepository logRepository;
    private static final int MAX_RETRIES = 3;

    public NotificationService(UserRepository userRepository,
                               NotificationStrategyFactory factory,
                               NotificationLogRepository logRepository) {
        this.userRepository = userRepository;
        this.factory = factory;
        this.logRepository = logRepository;
    }

    @Async
    public void processMessage(Message message) {

        if (message == null || message.getCategory() == null) {
            throw new IllegalArgumentException("Message or category cannot be null");
        }

        // Get all users
        List<User> users = userRepository.findAll();

        for (User user : users) {

            // Verify users subscribed to the category
            if (!shouldNotify(user, message)) {
                continue;
            }

            if (user.getChannels() == null) {
                continue;
            }

            for (Channel channel : user.getChannels()) {

                // Create Log
                NotificationLog log = createLog(user, message, channel);

                NotificationStrategy strategy = factory.getStrategy(channel);

                if (strategy == null) {
                    log.setStatus("FAILED");
                    log.setError("No strategy found for channel: " + channel);
                    logRepository.save(log);
                    continue;
                }

                int attempts = 0;
                boolean success = false;

                while (attempts < MAX_RETRIES && !success) {
                    try {
                        strategy.send(user, message.getContent());
                        log.setStatus("SUCCESS");
                        success = true;

                    } catch (Exception e) {
                        attempts++;

                        if (attempts == MAX_RETRIES) {
                            log.setStatus("FAILED");
                            log.setError(e.getMessage());
                        }
                    }
                }

                logRepository.save(log);
            }
        }
    }

    private boolean shouldNotify(User user, Message message) {
        return user != null &&
                user.isSubscribedTo(message.getCategory());
    }

    private NotificationLog createLog(User user, Message message, Channel channel) {
        NotificationLog log = new NotificationLog();
        log.setUserId(user.getId());
        log.setCategory(message.getCategory());
        log.setChannel(channel);
        log.setMessage(message.getContent());
        log.setTimestamp(LocalDateTime.now());
        return log;
    }

}