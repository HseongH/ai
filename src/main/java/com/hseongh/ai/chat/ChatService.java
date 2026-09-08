package com.hseongh.ai.chat;

import org.springframework.ai.chat.model.ChatResponse;

public interface ChatService {

    ChatResponse chat(String userInput);
}
