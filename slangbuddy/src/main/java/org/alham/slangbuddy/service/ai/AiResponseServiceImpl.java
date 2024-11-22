package org.alham.slangbuddy.service.ai;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.alham.slangbuddy.dto.SlangDTO;
import org.alham.slangbuddy.enums.Intensity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AiResponseServiceImpl implements AiResponseService{
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
        //TODO: API 키를 이용해 응답을 받아옴
        return "API 응답";
    }
}
