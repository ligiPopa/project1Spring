package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.UserEntity;
import com.example.demo.exceptions.UserServiceException;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class UserServiceImplTest {

    @InjectMocks
    UserServiceImpl userService;

    @Mock
    UserRepository userRepository;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    final void getUserByEmail() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId("id");
        userEntity.setIsMemberOfBand(true);

        when(userRepository.findByEmail(anyString())).thenReturn(userEntity);
        UserDTO userDTO = userService.getUserByEmail("test@test.com");
        assertNotNull(userDTO);
        assertEquals("id", userDTO.getUserId());
    }


    @Test
    final void getUserByEmail_UserEmailNotFoundException() {
        when(userRepository.findByEmail(anyString())).thenReturn(null);
        assertThrows(UserServiceException.class,
                () -> {
                    userService.getUserByEmail("test@test.com");
                });
    }

    @Test
    final void getAllSingers() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId("id");
        userEntity.setIsMemberOfBand(false);


        UserEntity userEntity1 = new UserEntity();
        userEntity1.setUserId("id1");
        userEntity1.setIsMemberOfBand(true);

        UserEntity userEntity2 = new UserEntity();
        userEntity2.setUserId("id2");
        userEntity2.setIsMemberOfBand(false);

        List<UserEntity> users = new ArrayList<>();
        users.add(userEntity);
        users.add(userEntity2);

        when(userRepository.findAllSingers()).thenReturn(users);

        List<UserDTO> singers = userService.findSingers();
        assertNotNull(singers);

        assertEquals("id", singers.get(0).getUserId());
        assertEquals(users.size(), singers.size());
    }


    @Test
    final void getAllBands() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId("id");
        userEntity.setIsMemberOfBand(true);


        UserEntity userEntity1 = new UserEntity();
        userEntity1.setUserId("id1");
        userEntity1.setIsMemberOfBand(false);

        UserEntity userEntity2 = new UserEntity();
        userEntity2.setUserId("id2");
        userEntity2.setIsMemberOfBand(true);

        List<UserEntity> users = new ArrayList<>();
        users.add(userEntity);
        users.add(userEntity2);

        when(userRepository.findAllBands()).thenReturn(users);

        List<UserDTO> singers = userService.findBands();
        assertNotNull(singers);

        assertEquals("id", singers.get(0).getUserId());
        assertEquals(users.size(), singers.size());
    }
}
