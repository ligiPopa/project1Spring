package com.example.demo;

import com.example.demo.dto.BandDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.model.request.UserRequestModel;
import com.example.demo.model.response.UserRest;
import com.example.demo.service.BandService;
import com.example.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @Autowired
    BandService bandService;

    @GetMapping(path = "/{id}")
    public UserRest getUser(@PathVariable String id) {
        UserDTO userDTO = userService.getUserByUserId(id);
        UserRest returnValue = new UserRest();
        BeanUtils.copyProperties(userDTO, returnValue);
        return returnValue;
        //return EntityModel.of(returnValue).add(userResourceLink).add(addressesResourceLink);
    }

    @PostMapping(path = "/create")
    public UserRest createUser(@RequestBody UserRequestModel userDetails) throws Exception {
        UserRest returnValue = new UserRest();
        UserDTO userDto = new UserDTO();

        BandDTO bandDTO = bandService.getBandByName(userDetails.getBandName());

        userDto.setIdBand(bandDTO.getBandId());
        userDto.setMemberOfBand(!userDetails.getBandName().isEmpty());

        BeanUtils.copyProperties(userDetails, userDto);
        UserDTO createdUser = userService.createUser(userDto);
        BeanUtils.copyProperties(createdUser, returnValue);

        //should be created/updated band
        if(!userDetails.getBandName().isEmpty())
            if(bandService.getBandByName(userDetails.getBandName()).getName().isEmpty()) {
                BandDTO bandDTO1 = new BandDTO();
                bandDTO1.setNrMembers(1);
                bandDTO1.setName(userDetails.getBandName());
                bandService.createBand(bandDTO);
            }
        else
            bandService.updateNumberOfMemberOfBand(userDetails.getBandName());


        return returnValue;
    }

}
