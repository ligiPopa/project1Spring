package com.example.demo.service;

import com.example.demo.dto.UserDTO;

public interface UserService {
    UserDTO getUser(String userId);

    UserDTO createUser(UserDTO userDTO);
}
