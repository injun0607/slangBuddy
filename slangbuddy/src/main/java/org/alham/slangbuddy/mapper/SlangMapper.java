package org.alham.slangbuddy.mapper;

import org.alham.slangbuddy.document.SlangDocument;
import org.alham.slangbuddy.dto.SlangDTO;
import org.springframework.stereotype.Component;

@Component
public class SlangMapper {

    public SlangDocument createDocumentNotLogin(SlangDTO slangDTO){
        return SlangDocument.builder()
                .name(slangDTO.getName())
                .description(slangDTO.getDescription())
                .intensity(slangDTO.getIntensity())
                .answer(slangDTO.getAnswer())
                .login(false)
                .build();
    }

    public SlangDocument createDocumentLogin(SlangDTO slangDTO){
        return SlangDocument.builder()
                .userId(slangDTO.getUserId())
                .name(slangDTO.getName())
                .description(slangDTO.getDescription())
                .intensity(slangDTO.getIntensity())
                .answer(slangDTO.getAnswer())
                .template(slangDTO.getTemplate())
                .login(true)
                .build();
    }


    public SlangDTO createDTOLogin(SlangDocument slangDocument){
        return SlangDTO.builder()
                .id(slangDocument.getId())
                .userId(slangDocument.getUserId())
                .name(slangDocument.getName())
                .description(slangDocument.getDescription())
                .intensity(slangDocument.getIntensity())
                .answer(slangDocument.getAnswer())
                .template(slangDocument.getTemplate())
                .login(slangDocument.isLogin())
                .build();
    }

    public SlangDTO createDTONotLogin(SlangDocument slangDocument){
        return SlangDTO.builder()
                .name(slangDocument.getName())
                .description(slangDocument.getDescription())
                .intensity(slangDocument.getIntensity())
                .answer(slangDocument.getAnswer())
                .login(slangDocument.isLogin())
                .build();
    }
}
