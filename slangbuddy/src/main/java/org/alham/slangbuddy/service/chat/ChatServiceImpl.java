package org.alham.slangbuddy.service.chat;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.alham.slangbuddy.dto.SlangDTO;
import org.alham.slangbuddy.enums.Template;
import org.alham.slangbuddy.service.slang.SlangService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService{

    private final SlangService slangService;

    /**
     * 유저 Id를 받아와서 해당 유저의 채팅 리스트를 가져온다.
     * 채팅 리스트는 - 해당 유저의 deleteFlag가 false permanent가 true를 가져온다.
     * @param userId
     * @return
     */
    @Override
    public List<SlangDTO> getChatList(String userId, Template template) {
        return slangService.findListByUserIdAndDeleteOrderByCreatedDate(userId, false, template);
    }

    @Override
    public SlangDTO talk(SlangDTO slangDTO) {
        if(slangDTO.isLogin()){
            return slangService.create(slangDTO);
        }else{
            return slangService.createNotLogin(slangDTO);
        }
    }

}
