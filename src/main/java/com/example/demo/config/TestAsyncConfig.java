package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executor;

/**
 * Test configuration that overrides the default async executor
 * to run tasks synchronously for reliable testing.
 */
@Configuration
public class TestAsyncConfig {

    @Bean
    public Executor taskExecutor() {
        return Runnable::run;
    }
}
