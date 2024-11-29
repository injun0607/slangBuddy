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
                .age(userDTO.getAge())
                .loginType(userDTO.getLoginType())
                .userRole(userDTO.getUserRole())
                .build();
    }

    public UserDTO createDTO(UserDocument userDocument) {
        return UserDTO.builder()
                .userId(userDocument.getUserId())
                .name(userDocument.getName())
                .age(userDocument.getAge())
                .loginType(userDocument.getLoginType())
                .userRole(userDocument.getUserRole())
                .build();
    }

}
