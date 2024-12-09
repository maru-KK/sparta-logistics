package com.sparta.logistics.auth.application.config;

import com.sparta.logistics.auth.application.util.AccessTokenDateGenerator;
import com.sparta.logistics.auth.application.util.DateGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public DateGenerator dateGenerator() {
        return new AccessTokenDateGenerator();
    }
}
