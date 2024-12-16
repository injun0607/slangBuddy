package org.alham.slangbuddy.config.advisor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.advisor.api.*;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Slf4j
//@Component
public class LoggingAdvisor implements CallAroundAdvisor, StreamAroundAdvisor, Ordered {

    @Override
    public AdvisedResponse aroundCall(AdvisedRequest advisedRequest, CallAroundAdvisorChain chain) {
        log.info("Request: {}", advisedRequest);
        AdvisedResponse response = chain.nextAroundCall(advisedRequest);
        log.info("Response: {}", response);
        return response;
    }

    @Override
    public Flux<AdvisedResponse> aroundStream(AdvisedRequest advisedRequest, StreamAroundAdvisorChain chain) {
        log.info("Streaming Request: {}", advisedRequest);
        Flux<AdvisedResponse> responseFlux = chain.nextAroundStream(advisedRequest);
        return responseFlux.doOnNext(response -> log.info("Streaming Response: {}", response));
    }

    @Override
    public String getName() {
        return "LoggingAdvisor";
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
