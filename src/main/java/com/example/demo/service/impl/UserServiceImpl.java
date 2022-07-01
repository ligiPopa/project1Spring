package com.example.demo.service.impl;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.UserEntity;
import com.example.demo.exceptions.UserServiceException;
import com.example.demo.model.response.ErrorMessages;
import com.example.demo.repository.BandRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.example.demo.utils.Utils;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    BandRepository bandRepository;

    @Autowired
    Utils utils;

    @Override
    public UserDTO getUserByUserId(String email) {
        UserEntity userEntity = userRepository.findByEmail(email);

        if (userEntity == null)
            throw new UserServiceException(email + " not found!");

        UserDTO returnValue = new UserDTO();
        BeanUtils.copyProperties(userEntity, returnValue);

        return returnValue;
    }

    @Override
    public UserDTO findByName(String userName) {
        UserEntity findUserByName= userRepository.findByEmail(userName);
        UserDTO userDTO = new UserDTO();
        if(findUserByName!=null)
            BeanUtils.copyProperties(findUserByName, userDTO);
        else
            //TODO make some exception
            System.out.println("User doesn't exist");
        return userDTO;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        if (userRepository.findByEmail(userDTO.getEmail()) != null)
            throw new UserServiceException("Record already exists");
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userDTO, userEntity);
        String publicUserId = utils.generateUserId(30);
        userEntity.setUserId(publicUserId);

        if(userEntity.getAge()<0)
            throw new UserServiceException("Age should be positive");
//        userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//        userEntity.setEmailVerificationToken(utils.generateEmailVerificationToken(publicUserId));
        UserEntity storedUserDetails = userRepository.save(userEntity);

        UserDTO returnValue = new UserDTO();
        BeanUtils.copyProperties(storedUserDetails, returnValue);

        // Send an email message to user to verify their email address
        //amazonSES.verifyEmail(returnValue);

        return returnValue;
    }

    public UserDTO updateUser(String userId, UserDTO userDTO){
        UserDTO returnValue = new UserDTO();

        UserEntity userEntity = userRepository.findByUserId(userId);

        if (userEntity == null)
            throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        userEntity.setAge(userDTO.getAge());
        userEntity.setUserId(userDTO.getUserId());
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setIdBand(userDTO.getIdBand());
        userEntity.setIsMemberOfBand(userDTO.isMemberOfBand());
        userEntity.setId(userDTO.getId());

        UserEntity updatedUserDetails = userRepository.save(userEntity);

        BeanUtils.copyProperties(updatedUserDetails, returnValue);

        return returnValue;
    }
}
