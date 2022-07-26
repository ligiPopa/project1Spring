package com.example.demo.controller;

import com.example.demo.UserController;
import com.example.demo.dto.UserDTO;
import com.example.demo.entity.BandEntity;
import com.example.demo.model.request.UserRequestModel;
import com.example.demo.model.request.UserUpdateEmailRequestModel;
import com.example.demo.model.response.UserRest;
import com.example.demo.service.impl.BandServiceImpl;
import com.example.demo.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class UserControllerTest {
    @InjectMocks
    UserController userController;

    @Mock
    UserServiceImpl userService;

    @Mock
    BandServiceImpl bandService;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    final void getBandByName() {
        UserRequestModel userRequestModel = new UserRequestModel();
        userRequestModel.setBandName("bandTest");
        userRequestModel.setEmail("userEmail.com");
        userRequestModel.setAge(23);
        userRequestModel.setMemberOfBand(false);

        UserDTO userDTO = new UserDTO();

        when(bandService.checkBandExistenceByName("bandTest")).thenReturn(true);
        doNothing().when(bandService).addBand(userDTO, "bandTest");
        userDTO.setEmail(userRequestModel.getEmail());

        userDTO.setUserId("userId");

        when(userService.createUser(any())).thenReturn(userDTO);

        try {
            UserRest userRest = userController.createUser(userRequestModel);

            assertNotNull(userRest);
            assertEquals(userDTO.getUserId(), userRest.getUserId());
            assertEquals(userDTO.getEmail(), userRest.getEmail());
            assertEquals(userDTO.getAge(), userRest.getAge());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    final void updateEmailUser(){
        BandEntity bandEntity = new BandEntity();
        bandEntity.setName("bandTest");

        //request
        UserUpdateEmailRequestModel userUpdateEmailRequestModel = new UserUpdateEmailRequestModel();
        userUpdateEmailRequestModel.setNewEmail("newUserEmail");
        userUpdateEmailRequestModel.setOldEmail("userEmail");

        //old user
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId("userId");
        userDTO.setEmail(userUpdateEmailRequestModel.getOldEmail());
        userDTO.setBandDetails(bandEntity);

        //updated user
        UserDTO updatedUserDTO = new UserDTO();
        updatedUserDTO.setEmail(userUpdateEmailRequestModel.getNewEmail());
        updatedUserDTO.setBandDetails(bandEntity);

        when(userService.getUserByEmail(userUpdateEmailRequestModel.getOldEmail())).thenReturn(userDTO);
        when(userService.updateUser(userDTO.getUserId(), userDTO)).thenReturn(updatedUserDTO);

        UserRest userRest = userController.updateEmailUser(userUpdateEmailRequestModel);

        assertNotNull(userRest);
        assertEquals(updatedUserDTO.getEmail(), userRest.getEmail());
        assertEquals(userUpdateEmailRequestModel.getNewEmail(), userRest.getEmail());
    }

    @Test
    final void getSingers(){
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId("user1");
        userDTO.setEmail("user1@gmail.com");
        userDTO.setIsMemberOfBand(false);

        List<UserDTO> userDTOList = new ArrayList<>();
        userDTOList.add(userDTO);
        when(userService.findSingers()).thenReturn(userDTOList);

        List<UserRest> userRestList = userController.getSingers();

        assertNotNull(userRestList);
        assertEquals(userDTOList.size(),userRestList.size());
        assertEquals(1, userRestList.size());
        assertEquals(false, userRestList.get(0).isMemberOfBand());
    }

    @Test
    final void getBands(){
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId("user1");
        userDTO.setEmail("user1@gmail.com");
        userDTO.setIsMemberOfBand(true);

        List<UserDTO> userDTOList = new ArrayList<>();
        userDTOList.add(userDTO);
        when(userService.findBands()).thenReturn(userDTOList);

        List<UserRest> userRestList = userController.getBands();

        assertNotNull(userRestList);
        assertEquals(userDTOList.size(),userRestList.size());
        assertEquals(1, userRestList.size());
        assertEquals(true, userRestList.get(0).isMemberOfBand());
    }
}