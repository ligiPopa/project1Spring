package com.example.demo.service.impl;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.UserEntity;
import com.example.demo.exceptions.UserServiceException;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDTO getUser(String email) {
        UserEntity userEntity = userRepository.findByEmail(email);

        if (userEntity == null)
            throw new UserServiceException(email);

        UserDTO returnValue = new UserDTO();
        BeanUtils.copyProperties(userEntity, returnValue);

        return returnValue;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        if (userRepository.findByEmail(userDTO.getEmail()) != null)
            throw new UserServiceException("Record already exists");


        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userDTO, userEntity);


//        String publicUserId = userDTO.generateUserId(30);
//        userEntity.setUserId(publicUserId);
//        userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//        userEntity.setEmailVerificationToken(utils.generateEmailVerificationToken(publicUserId));
//
//        UserEntity storedUserDetails = userRepository.save(userEntity);
//
//        //BeanUtils.copyProperties(storedUserDetails, returnValue);
//        UserDto returnValue  = modelMapper.map(storedUserDetails, UserDto.class);
//
//        // Send an email message to user to verify their email address
//        amazonSES.verifyEmail(returnValue);
//
//        return returnValue;
//    }

        return null;
    }
}
