package com.example.demo.service;

import com.example.demo.dto.BandDTO;
import com.example.demo.dto.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO getUserByEmail(String userEmail);

    //TODO maybe this method should be removed
    UserDTO findByName(String userName);

    UserDTO createUser(UserDTO userDTO);
    UserDTO updateUser(String userId, UserDTO userDTO);
    List<UserDTO> findSingers();

    List<UserDTO> findBands();
//    void createUserWithBand(String bandName, UserDTO userDTO);
//
//    void updateUserWithBand(String bandName, UserDTO userDTO);
   // BandDTO addMembers(UserDTO userDTO);
}
