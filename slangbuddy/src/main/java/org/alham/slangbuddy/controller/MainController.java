package org.alham.slangbuddy.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.alham.slangbuddy.dto.SlangDTO;
import org.alham.slangbuddy.service.ai.AiResponseService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
@RestController
public class MainController {

    private final AiResponseService aiResponseService;

    private final ChatClient chatClient;
    @PostMapping("/ai/response")
    public String aiResponse(@RequestBody SlangDTO slangDTO) {
        String aiResponse = aiResponseService.getAiResponse(slangDTO);
        System.out.println(aiResponse);

        return aiResponse;
    }


    @PostMapping("/ai/ham")
    public String aiHam(@RequestBody Map<String, String> map) {

        ChatResponse description = chatClient.prompt().user(map.get("description")).call().chatResponse();

        return description.toString();
    }



}
