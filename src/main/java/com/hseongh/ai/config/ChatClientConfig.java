package com.hseongh.ai.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatClientConfig {

    @Bean
    ChatClient defaultChatClient(ChatClient.Builder builder) {
        return builder.build();
    }
}
