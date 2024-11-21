package org.alham.slangbuddy.controller.slang;

import org.alham.slangbuddy.dto.SlangDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/slang")
public class SlangController {

    @GetMapping
    public String slang(){
        return "index";
    }

    @PostMapping("/create")
    public SlangDTO create(SlangDTO slangDTO){
        /**
         * slangDTO 받고 ->
         * slangAnswer 받고 ->
         * 
         */


        return new SlangDTO();
    }

}
