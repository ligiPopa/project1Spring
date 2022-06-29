package com.example.demo.service;

import com.example.demo.dto.UserDTO;

public interface UserService {
    UserDTO getUserByUserId(String userId);

    UserDTO createUser(UserDTO userDTO);
    UserDTO updateUser(String userId, UserDTO userDTO);
}
