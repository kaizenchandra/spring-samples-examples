package com.synechisveltiosi.springbootkaizen.taskexecutor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration(proxyBeanMethods = false)
public class MyTaskExecutorConfiguration2 {

    @Bean
    AsyncConfigurer asyncConfigurer(ExecutorService executorService) {
        return new AsyncConfigurer() {

            @Override
            public Executor getAsyncExecutor() {
                return executorService;
            }

        };
    }

    @Bean
    ExecutorService executorService() {
        return Executors.newCachedThreadPool();
    }

}