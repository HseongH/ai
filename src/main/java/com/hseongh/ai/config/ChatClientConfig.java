package com.hseongh.ai.config;

import com.hseongh.ai.advisor.ReReadingAdvisor;
import io.micrometer.observation.ObservationRegistry;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.client.advisor.ToolCallingAdvisor;
import org.springframework.ai.chat.client.advisor.observation.AdvisorObservationConvention;
import org.springframework.ai.chat.client.observation.ChatClientObservationConvention;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.model.chat.client.autoconfigure.ChatClientBuilderConfigurer;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ChatClientConfig {

  @Bean
  @Primary
  public ChatClient defaultChatClient(
      ChatModel chatModel,
      ChatClientBuilderConfigurer configurer,
      ObjectProvider<ObservationRegistry> observationRegistry,
      ObjectProvider<ChatClientObservationConvention> chatClientObservationConvention,
      ObjectProvider<AdvisorObservationConvention> advisorObservationConvention,
      ObjectProvider<ToolCallingAdvisor.Builder<?>> toolCallingAdvisorBuilder) {
    return buildChatClient(
        chatModel,
        configurer,
        observationRegistry,
        chatClientObservationConvention,
        advisorObservationConvention,
        toolCallingAdvisorBuilder);
  }

  private ChatClient buildChatClient(
      ChatModel chatModel,
      ChatClientBuilderConfigurer configurer,
      ObjectProvider<ObservationRegistry> observationRegistry,
      ObjectProvider<ChatClientObservationConvention> chatClientObservationConvention,
      ObjectProvider<AdvisorObservationConvention> advisorObservationConvention,
      ObjectProvider<ToolCallingAdvisor.Builder<?>> toolCallingAdvisorBuilder) {
    var chatClient =
        ChatClient.builder(
            chatModel,
            observationRegistry.getIfUnique(() -> ObservationRegistry.NOOP),
            chatClientObservationConvention.getIfUnique(),
            advisorObservationConvention.getIfUnique(),
            toolCallingAdvisorBuilder.getIfAvailable());

    return configurer
        .configure(
            chatClient
                .defaultAdvisors(new SimpleLoggerAdvisor(), new ReReadingAdvisor())
                .defaultSystem("Please answer in Korean."))
        .build();
  }
}
