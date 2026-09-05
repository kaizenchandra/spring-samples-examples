package com.synechisveltiosi.springbootkaizen.concept.methodlookup;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

/**
 * Demonstrates method lookup injection pattern in Spring.
 * This class shows how to use prototype-scoped beans within singleton-scoped beans.
 */
@Component
@Slf4j
public abstract class MethodLookupInjectionDemo {
    private static final String PROTOTYPE_SCOPE = "prototype";

    public interface PrototypeTask {
        void runTask();
    }

    /**
     * First implementation of PrototypeTask
     */
    @Component("impl1")
    @Scope(PROTOTYPE_SCOPE)
    public static class PrototypeTaskImpl1 implements PrototypeTask {
        private static final Logger log = LoggerFactory.getLogger(PrototypeTaskImpl1.class);
        private final int instanceId = System.identityHashCode(this);

        @Override
        public void runTask() {
            log.info("Running prototype task 1 with instance ID: {}", instanceId);
        }
    }


    /**
     * Second implementation of PrototypeTask
     */
    @Component("impl2")
    @Scope(PROTOTYPE_SCOPE)
    public static class PrototypeTaskImpl2 implements PrototypeTask {
        private static final Logger log = LoggerFactory.getLogger(PrototypeTaskImpl2.class);
        private final int instanceId = System.identityHashCode(this);

        @Override
        public void runTask() {
            log.info("Running prototype task 2 with instance ID: {}", instanceId);
        }
    }

    /**
     * Executes work by creating and comparing two prototype tasks.
     * Demonstrates that each call to createPrototypeTask creates a new instance.
     */
    public void doWork() {
        PrototypeTask prototypeTask1 = createPrototypeTask();
        PrototypeTask prototypeTask2 = createPrototypeTask();
        boolean sameRef = Objects.equals(prototypeTask1.hashCode(), prototypeTask2.hashCode());

        log.info(sameRef ? "Same reference" : "Different references");

        prototypeTask1.runTask();
        prototypeTask2.runTask();
    }

    @Lookup("impl1")
    public abstract PrototypeTask createPrototypeTask();
}

