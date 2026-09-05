package com.synechisveltiosi.springbootkaizen.taskexecutor;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Configuration(proxyBeanMethods = false)
public class MyTaskExecutorConfiguration {

    @Bean("applicationTaskExecutor")
    SimpleAsyncTaskExecutor applicationTaskExecutor() {
        return new SimpleAsyncTaskExecutor("app-");
    }
    @Bean("taskExecutor")
    ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setThreadNamePrefix("async-");
        return threadPoolTaskExecutor;
    }

    @Bean(defaultCandidate = false)
    @Qualifier("scheduledExecutorService")
    ScheduledExecutorService scheduledExecutorService() {
        return Executors.newSingleThreadScheduledExecutor();
    }

}