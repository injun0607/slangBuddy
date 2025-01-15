package org.alham.slangbuddy.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TestMainController {

    private final OpenAiChatModel openAiChatModel;


    @PostMapping("/chat")
    public Map<String, String> chat(@RequestBody String message) {
        Map<String, String> responses = new HashMap<>();

        String openAiResponse = openAiChatModel.call(message);
        responses.put("data", openAiResponse);
        System.out.println("openai(chatGPT) 응답: " + openAiResponse);

        return responses;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
