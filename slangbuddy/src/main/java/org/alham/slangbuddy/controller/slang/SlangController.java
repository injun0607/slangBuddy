package org.alham.slangbuddy.controller.slang;

import lombok.RequiredArgsConstructor;
import org.alham.slangbuddy.dto.SlangDTO;
import org.alham.slangbuddy.dto.SlangListDTO;
import org.alham.slangbuddy.service.slang.SlangService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/slang")
@RequiredArgsConstructor
public class SlangController {

    private final SlangService slangService;

    @GetMapping
    public String slang(){
        return "index";
    }

    /**
     * 슬랭 생성
     * @param slangDTO
     * @return
     */
    @PostMapping("/create")
    public SlangDTO create(@RequestBody SlangDTO slangDTO){
        return slangService.create(slangDTO);
    }

    /**
     * @param userId
     * @return
     */

    //TODO - userId 로 받는것 -> jwt 토큰에서 받는걸로 수정해야함
    @GetMapping("/find/{userId}")
    public List<SlangDTO> slangList(@PathVariable String userId){
        return slangService.findListByUserId(userId);
    }

    @PostMapping("/update/permanent")
    public List<SlangDTO> updatePermanent(@RequestBody SlangListDTO slangListDTO) {
        return slangService.updatePermanent(slangListDTO.getUserId(), slangListDTO.isPermanent(), slangListDTO.getSlangDTOList());
    }

    @GetMapping("/find/permanent")
    public List<SlangDTO> permanentSlangList(@RequestParam(name = "userId") String userId , @RequestParam(name = "permanent") boolean permanent){
        return slangService.findListByUserIdAndPermanent(userId, permanent);
    }


}
