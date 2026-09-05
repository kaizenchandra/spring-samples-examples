package com.synechisveltiosi.springbootkaizen;

import com.synechisveltiosi.springbootkaizen.concept.externalconfig.ConfigProps;
import com.synechisveltiosi.springbootkaizen.concept.externalconfig.O3_ValueConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestPropertySource(
        locations = "classpath:custom.properties",
        properties = {
                "app.title=Test Title",
                "app.version=1.0-TEST"
        }
)
class SpringBootKaizenApplicationTests {
    @Autowired
    private ConfigProps configProps;

    @Autowired
    private O3_ValueConfig valueConfig;

    @DynamicPropertySource
    static void dynamicProperties(DynamicPropertyRegistry registry) {
        registry.add("app.title", () -> "Dynamic Title");
        registry.add("app.version", () -> "2.0-DYNAMIC");
    }

    @Test
    void contextLoads() {
        assertEquals("Dynamic Title", configProps.getTitle());
        assertEquals("2.0-DYNAMIC", configProps.getVersion());

        assertEquals("Dynamic Title", valueConfig.getTitle());
        assertEquals("2.0-DYNAMIC", valueConfig.getVersion());
    }

}
