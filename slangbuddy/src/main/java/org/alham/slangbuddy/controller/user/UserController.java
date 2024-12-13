package org.alham.slangbuddy.controller.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.alham.slangbuddy.dto.UserDTO;
import org.alham.slangbuddy.service.user.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping("/create")
    public UserDTO createUser(@RequestBody UserDTO userDTO){
        return userService.saveUser(userDTO);
    }

    @GetMapping("/{userId}")
    public UserDTO getUser(@PathVariable String userId){
        return userService.findUserByUserId(userId);
    }

    /**
     * 유저 정보 가져오기
     * @return
     */
    @GetMapping("/info")
    public UserDTO getUserInfo(){
        //TODO - JWT 토큰을 가져와서 유저 정보를 조회해야함
        String userId = "userName";
        return userService.findUserByUserId(userId);
    }

    @PostMapping("/update/info")
    public UserDTO updateUserInfo(@RequestBody UserDTO userDTO){
        return userService.updateUserInfo(userDTO);
    }

    /**
     * 유저 특징 업데이트
     * @param userDTO
     * @return
     */
    @PostMapping("/update/features")
    public UserDTO updateUserFeatures(@RequestBody UserDTO userDTO){
        return userService.updateUserFeatures(userDTO);
    }


}
