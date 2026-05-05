package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executor;

@Configuration
public class TestAsyncConfig {

    @Bean
    public Executor taskExecutor() {
        return Runnable::run;
    }
}
