package org.alham.slangbuddy.dto;

import lombok.*;
import org.alham.slangbuddy.enums.UserLoginType;
import org.alham.slangbuddy.enums.UserRole;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserLoginInfoDTO{
    public String id;
    public String userId;
    public String name;
    public long age;
    private UserLoginType loginType;
    private UserRole userRole;


}
