package org.alham.slangbuddy.config.advisor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.advisor.api.AdvisedRequest;
import org.springframework.ai.chat.client.advisor.api.AdvisedResponse;
import org.springframework.ai.chat.client.advisor.api.CallAroundAdvisor;
import org.springframework.ai.chat.client.advisor.api.CallAroundAdvisorChain;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ChatAdvisor implements CallAroundAdvisor, Ordered {
    @Override
    public AdvisedResponse aroundCall(AdvisedRequest advisedRequest, CallAroundAdvisorChain chain) {
        // 데이터베이스 조회 로직
//        String dbResult = fetchFromDatabase(); // 데이터베이스에서 값 조회
//
//        // assistant 역할의 메시지 추가
//        advisedRequest.addMessage(new AssistantMessage(dbResult));
//
//        // 다음 어드바이저 또는 최종 호출로 전달
//        return chain.nextAroundCall(advisedRequest);
        return null;
    }

    @Override
    public String getName() {
        return "chatAdvisor";
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
