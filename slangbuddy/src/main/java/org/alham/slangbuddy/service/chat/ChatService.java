package org.alham.slangbuddy.service.chat;

import org.alham.slangbuddy.dto.SlangDTO;
import org.alham.slangbuddy.enums.Template;

import java.util.List;

public interface ChatService {

    public List<SlangDTO> getChatList(String userId, Template template);

    public SlangDTO talk(SlangDTO slangDTO);

}
