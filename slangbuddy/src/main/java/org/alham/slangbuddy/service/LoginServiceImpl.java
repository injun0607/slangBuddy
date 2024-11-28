package org.alham.slangbuddy.service;

import lombok.RequiredArgsConstructor;
import org.alham.slangbuddy.config.security.jwt.JwtUtil;
import org.alham.slangbuddy.config.security.jwt.TempOAuthReplacement;
import org.alham.slangbuddy.dto.UserLoginInfoDTO;
import org.alham.slangbuddy.repository.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService{

    private final TempOAuthReplacement oAuthReplacement;

    private final UserRepository userRepository;

    private final JwtUtil jwtUtil;

    /**
     * 유저 로그인 로직
     * TODO - 현재는 임시 로그인 로직 / 추후 OAUTH2 로직으로 변경 필요
     */
    @Override
    public String login(String userId) {
        UserLoginInfoDTO userInfo = oAuthReplacement.getAccessToken(userId);

        String jwtToken = jwtUtil.generateToken(UUID.randomUUID().toString(), userInfo.getUserId());

        return jwtToken;


    }
}
