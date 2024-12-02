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

    @PostMapping("/update/features")
    public UserDTO updateUserFeatures(@RequestBody UserDTO userDTO){
        return userService.updateUserFeatures(userDTO);
    }




}
