package org.alham.slangbuddy.service.ai;


import org.alham.slangbuddy.dto.SlangDTO;
import org.springframework.ai.chat.messages.Message;

import java.util.List;

public interface AiResponseService {

    public String getAiResponse(SlangDTO slangDTO, List<Message> messageList);
    public String getAiResponse(SlangDTO slangDTO);

}
