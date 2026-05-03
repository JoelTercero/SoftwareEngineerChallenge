package com.example.demo.controller;

import com.example.demo.domain.Category;
import com.example.demo.domain.Message;
import com.example.demo.domain.NotificationLog;
import com.example.demo.dto.MessageRequest;
import com.example.demo.repository.NotificationLogRepository;
import com.example.demo.service.NotificationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class NotificationControllerTest {

    private NotificationService notificationService;
    private NotificationLogRepository logRepository;
    private NotificationController controller;

    @BeforeEach
    void setUp() {
        notificationService = mock(NotificationService.class);
        logRepository = mock(NotificationLogRepository.class);

        controller = new NotificationController(notificationService, logRepository);
    }

    @Test
    void shouldSendNotificationSuccessfully() {
        MessageRequest request = new MessageRequest();
        request.setCategory("SPORTS");
        request.setContent("Hello");

        ResponseEntity<String> response = controller.send(request);

        verify(notificationService, times(1))
                .processMessage(any(Message.class));

        verify(notificationService).processMessage(argThat(msg ->
                msg.getCategory() == Category.SPORTS &&
                        msg.getContent().equals("Hello")
        ));

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void shouldThrowExceptionWhenMessageIsEmpty() {
        MessageRequest request = new MessageRequest();
        request.setCategory("SPORTS");
        request.setContent("");

        assertThrows(IllegalArgumentException.class, () -> {
            controller.send(request);
        });
    }

    @Test
    void shouldReturnLogs() {
        List<NotificationLog> logs = List.of(new NotificationLog());

        when(logRepository.findAllByOrderByTimestampDesc()).thenReturn(logs);

        List<NotificationLog> result = controller.getLogs();

        assertEquals(1, result.size());
        verify(logRepository, times(1)).findAllByOrderByTimestampDesc();
    }
}