package org.alham.slangbuddy.service.ai;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.alham.slangbuddy.dto.SlangDTO;
import org.alham.slangbuddy.enums.Intensity;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@Slf4j
@RequiredArgsConstructor
public class AiResponseServiceImpl implements AiResponseService{


//    private final ChatClient chatClient;

    /**
     * API 키를 이용해 응답을 받아옴
     * @param slangDTO
     * @return
     */
    @Override
    public String getAiResponse(SlangDTO slangDTO) {

        //slangDTO 의
        String name = slangDTO.getName();
        String description = slangDTO.getDescription();
        Intensity intensity = slangDTO.getIntensity();

        //TODO: API 키를 이용해 응답을 받아옴 - api 보내는걸 작성해야함

        return null;
    }


//    public Flux<String> chat(SlangDTO slangDTO) {
//
//        return this.chatClient.prompt()
//                .system(s -> s.param("user_name", slangDTO.getName())
//                            .param("intensity", slangDTO.getIntensity().name())
//                            .param("user_description", slangDTO.getDescription())
//                            .param("user_age","28")
//                            .param("user_gender","남자")
//                )
//                .user(slangDTO.getDescription())
////                .advisors(a -> a
////                        .param(CHAT_MEMORY_CONVERSATION_ID_KEY, chatId)
//                .stream().content();
//    }
}
