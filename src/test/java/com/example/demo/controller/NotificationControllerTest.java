package com.example.demo.controller;

import com.example.demo.domain.Category;
import com.example.demo.dto.MessageRequest;
import com.example.demo.dto.NotificationLogResponse;
import com.example.demo.exception.InvalidCategoryException;
import com.example.demo.service.NotificationLogService;
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
    private NotificationLogService logService;
    private NotificationController controller;

    @BeforeEach
    void setUp() {
        notificationService = mock(NotificationService.class);
        logService = mock(NotificationLogService.class);

        controller = new NotificationController(notificationService, logService);
    }

    @Test
    void shouldSendNotificationSuccessfully() {

        MessageRequest request = new MessageRequest();
        request.setCategory("SPORTS");
        request.setContent("Hello");

        ResponseEntity<String> response = controller.send(request);

        verify(notificationService).processMessage(argThat(msg ->
                msg.getCategory() == Category.SPORTS &&
                        msg.getContent().equals("Hello")
        ));

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void shouldReturnBadRequestForInvalidCategory() {
        MessageRequest request = new MessageRequest();
        request.setCategory("INVALID");
        request.setContent("Hello");

        assertThrows(InvalidCategoryException.class, () -> {
            controller.send(request);
        });
    }

    @Test
    void shouldReturnLogs() {

        List<NotificationLogResponse> logs = List.of(
                new NotificationLogResponse(
                        1L,
                        "SPORTS",
                        "EMAIL",
                        "Hello",
                        "SUCCESS",
                        "2026-01-01T10:00:00"
                )
        );

        when(logService.getLogs()).thenReturn(logs);

        List<NotificationLogResponse> result = controller.getLogs();

        assertEquals(1, result.size());
        verify(logService).getLogs();
    }
}