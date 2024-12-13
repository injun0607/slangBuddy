package org.alham.slangbuddy.service.user;

import org.alham.slangbuddy.dto.UserDTO;

public interface UserService {

    public UserDTO saveUser(UserDTO userDTO);

    public UserDTO findUserByUserId(String userId);

    public UserDTO updateUserFeatures(UserDTO userDTO);

    public UserDTO updateUserInfo(UserDTO userDTO);

}
