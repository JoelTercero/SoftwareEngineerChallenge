package com.example.demo.service;

import com.example.demo.domain.NotificationLog;
import com.example.demo.dto.NotificationLogResponse;
import com.example.demo.repository.NotificationLogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationLogService {

    private final NotificationLogRepository logRepository;

    public NotificationLogService(NotificationLogRepository logRepository) {
        this.logRepository = logRepository;
    }

    public List<NotificationLogResponse> getLogs() {
        return logRepository.findAllByOrderByTimestampDesc()
                .stream()
                .map(log -> new NotificationLogResponse(
                        log.getUserId(),
                        log.getCategory().name(),
                        log.getChannel().name(),
                        log.getMessage(),
                        log.getStatus(),
                        log.getTimestamp().toString()
                ))
                .toList();
    }
}
