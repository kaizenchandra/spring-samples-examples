package com.synechisveltiosi.springbootkaizen.concept.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class CLRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("CLRunner is running");
        System.out.println(Arrays.toString(args));
        boolean debug = Arrays.asList(args).contains("--debug");
        List<String> files = Arrays.stream(args).toList();
        if (debug) {
            System.out.println(files);
        }
    }
}
