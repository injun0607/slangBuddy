package org.alham.slangbuddy.controller.slang;

import lombok.RequiredArgsConstructor;
import org.alham.slangbuddy.dto.SlangDTO;
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

    @PostMapping("/create")
    public SlangDTO create(@RequestBody SlangDTO slangDTO){
        return slangService.create(slangDTO);
    }

    @GetMapping("/find/{userId}")
    public List<SlangDTO> slangList(@PathVariable Long userId){
        return slangService.findListByUserId(userId);
    }

    @PostMapping("/update/permanent/{userId}/{permanent}")
    public List<SlangDTO> updatePermanent(@PathVariable(name = "userId") Long userId, @PathVariable(name = "permanent") boolean permanent, @RequestBody List<String> slangIdList) {
        return slangService.updatePermanent(userId, permanent ,slangIdList);
    }

    @GetMapping("/find/permanent/{userId}/{permanent}")
    public List<SlangDTO> permanentSlangList(@PathVariable Long userId , @PathVariable boolean permanent){
        return slangService.findListByUserIdAndPermanent(userId, permanent);
    }




}
