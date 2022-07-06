package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserDTO getUserByUserId(String userId);

    //TODO maybe this method should be removed
    UserDTO findByName(String userName);

    UserDTO createUser(UserDTO userDTO);
    UserDTO updateUser(String userId, UserDTO userDTO);
}
