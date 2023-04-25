package com.example.tobyspringboot.study;

import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.context.annotation.*;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ConditionalTest {
    @Test
    public void conditional(){
        // true
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
        ac.register(Config1.class);
        ac.refresh();

        MyBean bean = ac.getBean(MyBean.class);

        // false
        AnnotationConfigApplicationContext ac2 = new AnnotationConfigApplicationContext();
        ac2.register(Config2.class);
        ac2.refresh();

        assertThrows(BeansException.class , ()->  {
            MyBean bean2 = ac2.getBean(MyBean.class);
        });

    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @Conditional(BooleanCondition.class)
    @interface BooleanConditional{
        boolean value();
    }

    static class BooleanCondition implements Condition {
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            Map<String, Object> annotationAttributes = metadata.getAnnotationAttributes(BooleanConditional.class.getName());
            return (Boolean)annotationAttributes.get("value");
        }
    }

    @Configuration
    @BooleanConditional(true)
    static class Config1 {
        @Bean
        MyBean myBean(){
            return new MyBean();
        }
    }

    @Configuration
    @BooleanConditional(false)
    static class Config2 {
        @Bean
        MyBean myBean(){
            return new MyBean();
        }
    }

    static class MyBean{}

    static class TrueCondition implements Condition {
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            return true;
        }
    }

    static class FalseCondition implements Condition {
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            return false;
        }
    }
}
