package com.example.demo;

import com.example.demo.dto.BandDTO;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.BandEntity;
import com.example.demo.model.request.UserRequestModel;
import com.example.demo.model.request.UserUpdateEmailRequestModel;
import com.example.demo.model.response.UserRest;
import com.example.demo.service.BandService;
import com.example.demo.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@RestController
@SecurityRequirement(name = "BasicAuthentication")
@RequestMapping("/users")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @Autowired
    BandService bandService;

    @GetMapping(path = "/{email}")
    public UserRest getUser(@PathVariable String email) {
        UserDTO userDTO = userService.getUserByEmail(email);
        UserRest returnValue = new UserRest();
        BeanUtils.copyProperties(userDTO, returnValue);
        return returnValue;
        //return EntityModel.of(returnValue).add(userResourceLink).add(addressesResourceLink);
    }

    @PostMapping(path = "/create")
    public UserRest createUser(@RequestBody UserRequestModel userDetails) throws Exception {
        UserRest returnValue = new UserRest();
        UserDTO userDto = new UserDTO();

        //BandDTO bandDTO = bandService.getBandByName(userDetails.getBandName());

       // userDto.setIdBand(bandDTO.getBandId());
        //userDto.setMemberOfBand(!userDetails.getBandName().isEmpty());

        BeanUtils.copyProperties(userDetails, userDto);
        if (userDetails.isMemberOfBand())
            //should be created/updated band
            if (!userDetails.getBandName().isEmpty())
                if ( ! bandService.checkBandExistenceByName(userDetails.getBandName())) {
                    //TODO move it to service!!!
                    BandEntity bandEntity = new BandEntity();
                    BandDTO createdBand = bandService.createBand(userDetails.getBandName(),userDto);
                    BeanUtils.copyProperties(createdBand, bandEntity);
                    userDto.setBandDetails(bandEntity);
                } else {
                    //TODO move it to service!!!
                    BandEntity bandEntity = new BandEntity();
                    BandDTO updatedBand = bandService.updateNumberOfMemberOfBand(userDetails.getBandName(), userDto);
                    BeanUtils.copyProperties(updatedBand, bandEntity);
                    userDto.setBandDetails(bandEntity);
                }

        UserDTO createdUser = userService.createUser(userDto);
        returnValue.getBandDetails().setName(createdUser.getBandDetails().getName());
        returnValue.getBandDetails().setNrMembers(createdUser.getBandDetails().getNrMembers());
        //TODO add user list values
        //returnValue.getBandDetails().setListOfUserDetails(createdUser.getBandDetails().getListOfUserDetails());
        returnValue.setUserId(createdUser.getUserId());
        returnValue.setAge(createdUser.getAge());
        returnValue.setEmail(createdUser.getEmail());
        //BeanUtils.copyProperties(createdUser.getBandDetails(), createdUser.getBandDetails());
       // returnValue.setBandDetails(createdUser.getBandDetails());

        return returnValue;
    }

    @PutMapping(path = "/update")
    public UserRest updateEmailUser(@RequestBody UserUpdateEmailRequestModel userUpdateEmailRequestModel) {
        UserRest returnValue = new UserRest();
        UserDTO userDTO = userService.getUserByEmail(userUpdateEmailRequestModel.getOldEmail());
        userDTO.setEmail(userUpdateEmailRequestModel.getNewEmail());
        UserDTO updatedUser = userService.updateUser(userDTO.getUserId(), userDTO);
        BeanUtils.copyProperties(updatedUser, returnValue);

        //TODO change it !!!
        returnValue.getBandDetails().setName(updatedUser.getBandDetails().getName());
        returnValue.getBandDetails().setNrMembers(updatedUser.getBandDetails().getNrMembers());
        returnValue.getBandDetails().setName(updatedUser.getBandDetails().getName());

        // TODO add users !!!!!!
        //for( updatedUser.getBandDetails())
        //returnValue.getBandDetails().setListOfUserDetails(updatedUser.getBandDetails().getListOfUserDetails());

        return returnValue;
    }

}
