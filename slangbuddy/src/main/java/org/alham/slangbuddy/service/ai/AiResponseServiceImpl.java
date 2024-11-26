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


    private final ChatClient chatClient;

    /**
     * API 키를 이용해 응답을 받아옴
     * @param slangDTO
     * @return
     */
    @Override
    public String getAiResponse(SlangDTO slangDTO) {

        //TODO: Flux 더 괜찮게 사용할수있는 방법 찾기
        return chat(slangDTO)
                .collectList()  // 모든 청크를 리스트로 수집
                .map(chunks -> String.join("", chunks))  // 청크들을 하나의 문자열로 결합
                .block();
    }


    public Flux<String> chat(SlangDTO slangDTO) {

        return this.chatClient.prompt()
                .system(s -> s.param("user_name", slangDTO.getName())
                            .param("intensity", slangDTO.getIntensity().name())
                            .param("user_description", slangDTO.getDescription())
                            .param("user_age","28")
                            .param("user_gender","남자")
                )
                .user(slangDTO.getDescription())
//                .advisors(a -> a
//                        .param(CHAT_MEMORY_CONVERSATION_ID_KEY, chatId)
                .stream().content();
    }
}
