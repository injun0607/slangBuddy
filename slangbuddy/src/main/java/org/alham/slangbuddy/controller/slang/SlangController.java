package org.alham.slangbuddy.controller.slang;

import lombok.RequiredArgsConstructor;
import org.alham.slangbuddy.dto.SlangDTO;
import org.alham.slangbuddy.service.slang.SlangService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public SlangDTO create(SlangDTO slangDTO){
        /**
         * slangDTO 받고 ->
         * slangAnswer 받고 ->
         */

        return slangService.create(slangDTO);
    }




}
