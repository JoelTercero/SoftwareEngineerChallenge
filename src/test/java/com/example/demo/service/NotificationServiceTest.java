package com.example.demo.service;

import com.example.demo.domain.*;
import com.example.demo.factory.NotificationStrategyFactory;
import com.example.demo.repository.NotificationLogRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.strategy.NotificationStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;

public class NotificationServiceTest {

    private UserRepository userRepository;
    private NotificationStrategyFactory factory;
    private NotificationLogRepository logRepository;
    private NotificationService notificationService;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        factory = mock(NotificationStrategyFactory.class);
        logRepository = mock(NotificationLogRepository.class);

        notificationService = new NotificationService(userRepository, factory, logRepository);
    }

    @Test
    void shouldSendNotificationToSubscribedUser() {

        User user = new User(
                1L,
                "Joel",
                "joel@hotmail.com",
                "123456",
                List.of(Category.SPORTS),
                List.of(Channel.EMAIL)
        );

        Message message = new Message(Category.SPORTS, "Game tonight!");

        NotificationStrategy strategy = mock(NotificationStrategy.class);

        when(userRepository.getUsers()).thenReturn(List.of(user));
        when(factory.getStrategy(Channel.EMAIL)).thenReturn(strategy);

        notificationService.processMessage(message);

        verify(strategy, times(1)).send(user, "Game tonight!");
        verify(logRepository, times(1)).save(any(NotificationLog.class));
    }

    @Test
    void shouldNotSendNotificationIfUserNotSubscribed() {

        User user = new User(
                1L,
                "Joel",
                "joel@hotmail.com",
                "123456",
                List.of(Category.FINANCE),
                List.of(Channel.EMAIL)
        );

        Message message = new Message(Category.SPORTS, "Game tonight!");

        when(userRepository.getUsers()).thenReturn(List.of(user));

        notificationService.processMessage(message);

        verify(factory, never()).getStrategy(any());
        verify(logRepository, never()).save(any());
    }

    @Test
    void shouldHandleStrategyFailure() {

        User user = new User(
                1L,
                "Joel",
                "joel@hotmail.com",
                "123456",
                List.of(Category.SPORTS),
                List.of(Channel.EMAIL)
        );

        Message message = new Message(Category.SPORTS, "Game tonight!");

        NotificationStrategy strategy = mock(NotificationStrategy.class);

        when(userRepository.getUsers()).thenReturn(List.of(user));
        when(factory.getStrategy(Channel.EMAIL)).thenReturn(strategy);

        doThrow(new RuntimeException("Error sending")).when(strategy).send(any(), any());

        notificationService.processMessage(message);

        verify(logRepository, times(1)).save(argThat(log ->
                log.getStatus().equals("FAILED")
        ));
    }
}
