package com.example.demo.controller;

import com.example.demo.dto.UserDTO;
import com.example.demo.model.request.UserRequestModel;
import com.example.demo.model.response.UserRest;
import com.example.demo.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping(path = "/{id}")
    public UserRest getUser(@PathVariable String id) {
        UserDTO userDTO = userService.getUserByUserId(id);
        UserRest returnValue = new UserRest();
        BeanUtils.copyProperties(userDTO, returnValue);
        return returnValue;
        //return EntityModel.of(returnValue).add(userResourceLink).add(addressesResourceLink);
    }

    @PostMapping
    public UserRest createUser(@RequestBody UserRequestModel userDetails) throws Exception {
        UserRest returnValue = new UserRest();
        BeanUtils.copyProperties(userDetails, returnValue);
        return returnValue;
    }

}
