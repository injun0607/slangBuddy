package org.alham.slangbuddy.service.ai;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.alham.slangbuddy.config.AiPrompt;
import org.alham.slangbuddy.dto.SlangDTO;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

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
    public String getAiResponse(SlangDTO slangDTO, List<Message> messageList) {

        //TODO: Flux 더 괜찮게 사용할수있는 방법 찾기
        return chat(slangDTO,messageList)
                .collectList()  // 모든 청크를 리스트로 수집
                .map(chunks -> String.join("", chunks))  // 청크들을 하나의 문자열로 결합
                .block();
    }

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



    public Flux<String> chat(SlangDTO slangDTO,List<Message> messageList) {

        //모드에 따라 다른 대화를 제공
        switch (slangDTO.getTemplate()) {
            case BASIC:
                return chatClient.prompt().system(s -> s.text(AiPrompt.DEFAULT_SYSTEM_CHAT)
                        .param("user_name", slangDTO.getName())
                        .param("intensity", slangDTO.getIntensity().name())
                        .param("user_description", slangDTO.getDescription())
                        .param("user_age", "28")).stream().content();

            case HAM:
                return chatClient.prompt().system(s -> s.text(AiPrompt.HAM_SYSTEM_CHAT))
                        .user(slangDTO.getDescription())
                        .messages(messageList)
                        .stream()
                        .content();
            case META:
                return chatClient.prompt().system(s -> s.text(AiPrompt.META_SYSTEM_CHAT)
                        .param("user_name", slangDTO.getName()))
                        .user(slangDTO.getDescription())
                        .messages(messageList)
                        .stream()
                        .content();
            default:
                return chatClient.prompt().system(s -> s.text(AiPrompt.DEFAULT_SYSTEM_CHAT)
                        .param("user_name", slangDTO.getName())
                        .param("intensity", slangDTO.getIntensity().name())
                        .param("user_description", slangDTO.getDescription())
                        .param("user_age", "28")).stream().content();

        }
    }

    public Flux<String> chat(SlangDTO slangDTO) {

        //모드에 따라 다른 대화를 제공
        switch (slangDTO.getTemplate()) {
            case BASIC:
                return chatClient.prompt().system(s -> s.text(AiPrompt.DEFAULT_SYSTEM_CHAT)
                        .param("user_name", slangDTO.getName())
                        .param("intensity", slangDTO.getIntensity().name())
                        .param("user_description", slangDTO.getDescription())
                        .param("user_age", "28")).stream().content();

            case HAM:
                return chatClient.prompt().system(s -> s.text(AiPrompt.HAM_SYSTEM_CHAT))
                        .user(slangDTO.getDescription())
                        .stream()
                        .content();
            case META:
                return chatClient.prompt().system(s -> s.text(AiPrompt.META_SYSTEM_CHAT)
                                .param("user_name", slangDTO.getName()))
                        .user(slangDTO.getDescription())
                        .stream()
                        .content();
            default:
                return chatClient.prompt().system(s -> s.text(AiPrompt.DEFAULT_SYSTEM_CHAT)
                        .param("user_name", slangDTO.getName())
                        .param("intensity", slangDTO.getIntensity().name())
                        .param("user_description", slangDTO.getDescription())
                        .param("user_age", "28")).stream().content();

        }
    }
}
