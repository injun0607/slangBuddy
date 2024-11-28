package org.alham.slangbuddy.config.security.jwt;


import lombok.RequiredArgsConstructor;
import org.alham.slangbuddy.document.UserDocument;
import org.alham.slangbuddy.dto.UserLoginInfoDTO;
import org.alham.slangbuddy.repository.user.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class TempOAuthReplacement {

    private final UserRepository userRepository;

    //OAUTH 인증시 반환하는 토큰 내부 이름 (임시 처리)
    public UserLoginInfoDTO getAccessToken(String userId) {

        UserDocument findUser = userRepository.findUserByUserId(userId);

        return UserLoginInfoDTO.builder()
                .id(findUser.getId())
                .name(findUser.getName())
                .userId(findUser.getUserId())
                .age(findUser.getAge())
                .loginType(findUser.getLoginType())
                .userRole(findUser.getUserRole())
                .build();
    }

}
