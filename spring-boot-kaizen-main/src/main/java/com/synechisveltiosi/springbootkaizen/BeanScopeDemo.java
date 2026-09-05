package com.synechisveltiosi.springbootkaizen;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

@Component
@Slf4j
public class BeanScopeDemo {

    @Component
    @Scope("singleton")
    public static class SingletonBean {
        private final int instanceId = System.identityHashCode(this);

        @PostConstruct
        public void init() {
            log.info("SingletonBean created with ID: {}", instanceId);
        }

        public int getId() {
            return instanceId;
        }
    }

    @Component
    @Scope("prototype")
    public static class PrototypeBean {
        private final int instanceId = System.identityHashCode(this);

        @PostConstruct
        public void init() {
            log.info("PrototypeBean created with ID: {}", instanceId);
        }

        public int getId() {
            return instanceId;
        }
    }

    @Component
    @RequestScope
    public static class RequestScopedBean {
        private final int instanceId = System.identityHashCode(this);

        @PostConstruct
        public void init() {
            log.info("RequestScopedBean created with ID: {}", instanceId);
        }

        public int getId() {
            return instanceId;
        }
    }

    @Component
    @SessionScope
    public static class SessionScopedBean {
        private final int instanceId = System.identityHashCode(this);

        @PostConstruct
        public void init() {
            log.info("SessionScopedBean created with ID: {}", instanceId);
        }

        public int getId() {
            return instanceId;
        }
    }
}
