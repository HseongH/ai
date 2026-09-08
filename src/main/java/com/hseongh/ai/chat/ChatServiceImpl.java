package com.hseongh.ai.chat;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.api.OllamaChatOptions;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final ChatModel chatModel;

    @Override
    public ChatResponse chat(String userInput) {
        return chatModel.call(
                new Prompt(
                        userInput,
                        OllamaChatOptions.builder()
                                .model("gemma3:4b")
                                .temperature(0.4)
                                .build()));
    }
}
