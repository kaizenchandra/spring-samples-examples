package com.synechisveltiosi.springbootkaizen.concept.lazy;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Lazy
public class LazyInitClassLevel {

    public LazyInitClassLevel() {
        log.info("LazyInit bean is being created...");
    }

    public String getMessage() {
        return "This bean was initialized lazily!";
    }
}
