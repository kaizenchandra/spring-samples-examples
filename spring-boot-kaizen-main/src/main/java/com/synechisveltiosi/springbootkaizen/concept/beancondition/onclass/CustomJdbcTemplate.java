package com.synechisveltiosi.springbootkaizen.concept.beancondition.onclass;

import jakarta.annotation.PostConstruct;

public class CustomJdbcTemplate {
    public CustomJdbcTemplate() {
        System.out.println("CustomJdbcTemplate bean is being created...");
    }
    @PostConstruct
    public void init() {
        System.out.println("CustomJdbcTemplate init");
    }
}
