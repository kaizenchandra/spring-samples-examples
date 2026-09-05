package com.synechisveltiosi.springbootkaizen;

import com.synechisveltiosi.springbootkaizen.concept.ioc.AppConfig;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.metrics.buffering.BufferingApplicationStartup;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.metrics.jfr.FlightRecorderApplicationStartup;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.io.PrintStream;
import java.util.Map;
@EnableAsync
@EnableConfigurationProperties(AppConfig.class)
@SpringBootApplication
public class SpringBootKaizenApplication implements CommandLineRunner {

    private final ChildService childService;

    public SpringBootKaizenApplication(ChildService childService) {
        this.childService = childService;
    }

    public static void main(String[] args) {
//        new SpringApplicationBuilder()
//                .sources(Parent.class)
//                .child(SpringBootKaizenApplication.class)
//                .bannerMode(Banner.Mode.CONSOLE)
//                .logStartupInfo(false)
//                .lazyInitialization(true)
//                .banner(getBanner())
//                .run(args);

//        try (ConfigurableApplicationContext ctx = SpringApplication.run(SpringBootKaizenApplication.class, args)) {
//            // do something quick and exit — closes immediately
//            System.exit(SpringApplication.exit(ctx));
//        }


        // SpringApplication.run(SpringBootKaizenApplication.class, args);
        SpringApplication app = new SpringApplication(SpringBootKaizenApplication.class);
////        app.setBanner(getBanner());
////        app.setLazyInitialization(true);
////        app.setLogStartupInfo(true);
////        app.setBannerMode(Banner.Mode.LOG);
        //app.setApplicationStartup(new BufferingApplicationStartup(2048));
        app.setApplicationStartup(new FlightRecorderApplicationStartup());
       // app.setDefaultProperties(System.getProperties());
        app.setDefaultProperties(Map.of("spring.config.name", "application,application-dev"));
        app.run(args);
    }

    @Override
    public void run(String... args) {
        childService.run();
        asycTask();
    }

    @Async
    public void asycTask() {
        System.out.println("------------------------------------");
        System.out.println(Thread.currentThread().getName()+"Async task is running");
        System.out.println("------------------------------------");

    }


    @Bean
    public ExitCodeGenerator exitCodeGenerator() {
        return () -> 42;
    }

    private static Banner getBanner() {
        return new Banner() {
            @Override
            public void printBanner(Environment environment, Class<?> sourceClass, PrintStream out) {
                out.println("=================================================");
                out.println("             Spring Boot Kaizen                   ");
                out.println("=================================================");
            }
        };
    }


}

@Configuration
 class Parent {

    @Bean
    public String parentBean() {
        return "I am Parent Bean";
    }
}


@Service
 class ParentService {

    private final String parentBean;

    public ParentService(String parentBean) {
        this.parentBean = parentBean;
    }

    public void show() {
        System.out.println("ParentService → " + parentBean);
    }
}
@Service
 class ChildService {

    private final ParentService parentService;

    public ChildService(ParentService parentService) {
        this.parentService = parentService;
    }

    public void run() {
        System.out.println("ChildService → calling ParentService");
        parentService.show();
    }
}
