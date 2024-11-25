package org.alham.slangbuddy.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.alham.slangbuddy.dto.SlangDTO;
import org.alham.slangbuddy.service.ai.AiResponseService;
import org.springframework.ai.chat.client.ChatClient;
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

        return aiResponseService.getAiResponse(slangDTO);
    }



}
