package com.synechisveltiosi.springbootkaizen.concept;

import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AccessAppArgs {

    public AccessAppArgs(ApplicationArguments args) {
        System.out.println("Accessing application arguments");
        boolean debug = args.containsOption("debug");
        List<String> files = args.getNonOptionArgs();
        if (debug) {
            System.out.println(files);
        }
        // if run with "--debug logfile.txt" prints ["logfile.txt"]
    }
}
