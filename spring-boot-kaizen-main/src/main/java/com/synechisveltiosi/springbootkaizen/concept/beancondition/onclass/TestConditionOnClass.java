package com.synechisveltiosi.springbootkaizen.concept.beancondition.onclass;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TestConditionOnClass implements CommandLineRunner {
    private final CustomRestTemplate customRestTemplate;
    private final CustomJdbcTemplate customJdbcTemplate;

    public TestConditionOnClass(CustomRestTemplate customRestTemplate, CustomJdbcTemplate customJdbcTemplate) {
        this.customRestTemplate = customRestTemplate;
        this.customJdbcTemplate = customJdbcTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Testing @ConditionalOnClass configuration:");
        System.out.println("CustomRestTemplate is " + (customRestTemplate == null ? "not" : "") + " available");
        System.out.println("CustomJdbcTemplate is " + (customJdbcTemplate == null ? "not" : "") + " available");
    }
}
