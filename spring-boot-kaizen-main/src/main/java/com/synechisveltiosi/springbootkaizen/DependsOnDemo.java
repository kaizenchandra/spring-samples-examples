package com.synechisveltiosi.springbootkaizen;

import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DependsOnDemo {

    @Component("firstBean")
    public static class FirstBean {
        public FirstBean() {
            log.info("FirstBean is initialized");
        }
    }

    @Component("secondBean")
    @DependsOn(value = "firstBean")
    public static class SecondBean {
        public SecondBean() {
            log.info("SecondBean is initialized after FirstBean");
        }
    }

    @Component
    @DependsOn(value = {"firstBean", "secondBean"})
    public static class MainBean {
        public MainBean() {
            log.info("MainBean is initialized after both FirstBean and SecondBean");
        }
    }
}
