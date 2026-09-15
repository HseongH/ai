package com.hseongh.ai.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class ChatService {

  private final ChatClient chatClient;

  public String chat(String message) {
    return chatClient.prompt().user(message).call().content();
  }

  public Flux<String> stream(String message) {
    return chatClient.prompt().user(message).stream().content();
  }
}
