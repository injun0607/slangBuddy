package org.alham.slangbuddy.controller.chat;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.alham.slangbuddy.dto.SlangDTO;
import org.alham.slangbuddy.enums.Template;
import org.alham.slangbuddy.service.chat.ChatService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatController {

    private final ChatService chatService;

    /**
     * 템플릿에 따른 대화하면
     * @param template
     * @return 저장된 채팅(permanent)포함 리스트
     */
    @GetMapping("/{template}")
    public List<SlangDTO> view(@PathVariable("template") String template){

        String userId = "67493344f1d5406fadb1d095";
        //TODO - 유저가 접속시 해당 유저의 idx를 받아와서 idx 정보를 통해 조회해야한다.
        //TODO - 템플릿과 저장된 정보를 가져와야함.
        Template tem = Template.getTemplate(template);
        return chatService.getChatList(userId, tem);

    }

    /**
     * 대화하기
     * @return aiResponse
     */
    @PostMapping("/talk")
    public SlangDTO talk(@RequestBody SlangDTO slangDTO){
        return chatService.talk(slangDTO);
    }

    /**
     * 대화하기 - 비로그인 사용자
     * @return aiResponse
     */
    public SlangDTO talkNotLogin(@RequestBody SlangDTO slangDTO){
        return chatService.talk(slangDTO);
    }

    @PostMapping("/update/permanent/{permanent}")
    public List<SlangDTO> updatePermanent(@RequestBody List<SlangDTO> slangDTOList,@PathVariable(name="permanent") boolean permanent){
        String userId = "67493344f1d5406fadb1d095";
        //TODO - 유저가 접속시 해당 유저의 idx를 받아와서 idx 정보를 통해 조회해야한다.
        //TODO - 템플릿과 저장된 정보를 가져와야함.


        chatService.updatePermanent(userId,permanent,slangDTOList);
        return null;
    }


}
