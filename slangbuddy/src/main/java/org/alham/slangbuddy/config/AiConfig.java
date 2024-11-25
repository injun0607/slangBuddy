package org.alham.slangbuddy.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.PromptChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.QuestionAnswerAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;

@Configuration
public class AiConfig {

    @Bean
    ChatClient chatClient(ChatClient.Builder builder) {
        return builder.defaultSystem("""
                         유저 이름: {user_name}, 나이: {user_age}, 성별: {user_gender} 
                         유저 상황: {user_description} 
                         욕의 강도: {intensity}
                         너는 유저가 처한 상황에 공감하며 위로의 말을 300자 정도로 작성하되, `intensity`에 따라 다음과 같이 욕을 섞어 표현한다:
                         - 강도 LOW : 욕 없이 위로와 공감 중심으로 작성.
                         - 강도 MIDDLE : 가벼운 욕설을 1~2회 추가하여 유머와 공감을 더함.
                         - 강도 HIGH : 강한 욕설을 자연스럽게 섞어 상대방의 분노를 대신 표현하며, 유저의 편에 서서 함께 분노한다.
                         
                         답변은 따뜻한 말투와 인간적인 공감으로 작성하며, 유저가 상황에 대해 위로받고 기운을 차릴 수 있도록 한다.
					""")
//                .defaultAdvisors(
//                        new PromptChatMemoryAdvisor(chatMemory), // Chat Memory
//                        // new VectorStoreChatMemoryAdvisor(vectorStore)),
//
//                        new QuestionAnswerAdvisor(vectorStore, SearchRequest.defaults()), // RAG
//                        // new QuestionAnswerAdvisor(vectorStore, SearchRequest.defaults()
//                        // 	.withFilterExpression("'documentType' == 'terms-of-service' && region in ['EU', 'US']")),
//
//                        new LoggingAdvisor())

//                .defaultFunctions("getBookingDetails", "changeBooking", "cancelBooking") // FUNCTION CALLING

                .build();
    }




}
