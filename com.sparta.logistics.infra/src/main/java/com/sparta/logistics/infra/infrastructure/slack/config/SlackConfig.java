package com.sparta.logistics.infra.infrastructure.slack.config;

import com.slack.api.Slack;
import com.slack.api.methods.MethodsClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SlackConfig {

    @Value("${sns.slack.token}")
    private String token;

    @Bean
    public MethodsClient methodsClient() {
        Slack slack = Slack.getInstance();
        return slack.methods(token);
    }
}
