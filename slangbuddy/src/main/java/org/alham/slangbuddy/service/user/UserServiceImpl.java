package org.alham.slangbuddy.service.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.alham.slangbuddy.document.UserDocument;
import org.alham.slangbuddy.dto.UserDTO;
import org.alham.slangbuddy.mapper.UserMapper;
import org.alham.slangbuddy.repository.user.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    @Override
    public UserDTO saveUser(UserDTO userDTO) {

        UserDocument userDocument = userMapper.createDocument(userDTO);
        UserDocument save = userRepository.save(userDocument);

        return userMapper.createDTO(save);
    }


    @Override
    public UserDTO findUserByUserId(String userId) {

        UserDocument userDocument = userRepository.findUserByUserId(userId);

        return userMapper.createDTOtWithFeature(userDocument);
    }

    @Override
    public UserDTO updateUserFeatures(UserDTO userDTO) {

        UserDocument userDocument = userRepository.findById(userDTO.getId()).orElseThrow();
        userDocument.updateFeatures(userDTO.getFeatures());
        UserDocument save = userRepository.save(userDocument);
        return userMapper.createDTO(save);
    }
}
