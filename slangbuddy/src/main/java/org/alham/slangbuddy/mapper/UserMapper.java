package org.alham.slangbuddy.mapper;

import org.alham.slangbuddy.document.UserDocument;
import org.alham.slangbuddy.dto.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDocument createDocument(UserDTO userDTO) {
        return UserDocument.builder()
                .userId(userDTO.getUserId())
                .name(userDTO.getName())
                .nickName(userDTO.getNickName())
                .age(userDTO.getAge())
                .loginType(userDTO.getLoginType())
                .userRole(userDTO.getUserRole())
                .build();
    }

    public UserDTO createDTOtWithFeature(UserDocument userDocument){
        UserDTO dto = UserDTO.builder()
                .id(userDocument.getId())
                .userId(userDocument.getUserId())
                .name(userDocument.getName())
                .nickName(userDocument.getNickName())
                .age(userDocument.getAge())
                .loginType(userDocument.getLoginType())
                .userRole(userDocument.getUserRole())
                .build();

        dto.updateFeatures(userDocument.getFeatures());
        return dto;
    }

    public UserDTO createDTO(UserDocument userDocument) {
        return UserDTO.builder()
                .id(userDocument.getId())
                .userId(userDocument.getUserId())
                .name(userDocument.getName())
                .nickName(userDocument.getNickName())
                .age(userDocument.getAge())
                .loginType(userDocument.getLoginType())
                .userRole(userDocument.getUserRole())
                .build();
    }

}
