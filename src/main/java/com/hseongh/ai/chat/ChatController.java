package com.hseongh.ai.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequiredArgsConstructor
public class ChatController {

  private final ChatService chatService;

  @GetMapping("/chat")
  public String chat(@RequestParam String message) {
    return chatService.chat(message);
  }

  @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Flux<String> streamChat(@RequestParam String message) {
    return chatService.stream(message);
  }
}
