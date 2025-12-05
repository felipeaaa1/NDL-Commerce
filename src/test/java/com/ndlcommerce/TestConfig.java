package com.ndlcommerce;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {

    @Bean
    BeanFactoryPostProcessor registerManualScanForTests() {
        return beanFactory -> {
            var registry = (BeanDefinitionRegistry) beanFactory;
            new NdlCommerceApplication().genericApplicationContext(registry);
        };
    }
}
