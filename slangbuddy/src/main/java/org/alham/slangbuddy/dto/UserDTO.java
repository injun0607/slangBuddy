package org.alham.slangbuddy.dto;

import lombok.*;
import org.alham.slangbuddy.enums.UserLoginType;
import org.alham.slangbuddy.enums.UserRole;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

    private String id;

    private String name;

    private String nickName;

    private String userId;

    private int age;

    private UserLoginType loginType;

    private UserRole userRole;

    private List<String> features = new ArrayList<>();


    @Builder
    public UserDTO(String name, String userId, int age, UserLoginType loginType, UserRole userRole){
        this.name = name;
        this.userId = userId;
        this.age = age;
        this.loginType = loginType;
        this.userRole = userRole;
    }

    public void updateFeatures(List<String> features){
        this.features = features;
    }

}
