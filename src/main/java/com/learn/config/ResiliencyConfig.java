package com.learn.config;

import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
@Slf4j
public class ResiliencyConfig implements BeanPostProcessor {

    @Autowired
    private RateLimiterRegistry rateLimiterRegistry;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        // Fetch api methods
        RateLimiterConfig customConfig = RateLimiterConfig.custom()
                .limitForPeriod(2)
                .limitRefreshPeriod(Duration.ofSeconds(15))
                .timeoutDuration(Duration.ofSeconds(10))
                .build();

        rateLimiterRegistry.rateLimiter("employeeDetails", customConfig)
                .getEventPublisher()
                .onFailure(event -> log.error("Rate limiter failure: RL name {} RL event Type : {}", event.getRateLimiterName(), event.getEventType()));

        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }
}
