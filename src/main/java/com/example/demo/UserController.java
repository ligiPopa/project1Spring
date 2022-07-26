package com.example.demo;

import com.example.demo.dto.UserDTO;
import com.example.demo.model.request.UserRequestModel;
import com.example.demo.model.request.UserUpdateEmailRequestModel;
import com.example.demo.model.response.UserRest;
import com.example.demo.service.BandService;
import com.example.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.stream.Collectors;

@RestController
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
    }

    @PostMapping(path = "/create")
    public UserRest createUser(@RequestBody UserRequestModel userDetails) throws Exception {
        UserRest returnValue = new UserRest();
        UserDTO userDto = new UserDTO();

        BeanUtils.copyProperties(userDetails, userDto);
        if (userDetails.isMemberOfBand())
            //should be created/updated band
            if (!userDetails.getBandName().isEmpty())
                if ( ! bandService.checkBandExistenceByName(userDetails.getBandName())) {
                    bandService.addBand(userDto, userDetails.getBandName());
                } else {
                    bandService.updateBandWithNewMember(userDto, userDetails.getBandName());
                }

        UserDTO createdUser = userService.createUser(userDto);
        if ( userDetails.isMemberOfBand()) {
            returnValue.getBandDetails().setName(createdUser.getBandDetails().getName());
            returnValue.getBandDetails().setNrMembers(createdUser.getBandDetails().getNrMembers());
        }
        //TODO add user list values
        returnValue.setUserId(createdUser.getUserId());
        returnValue.setAge(createdUser.getAge());
        returnValue.setEmail(createdUser.getEmail());
        returnValue.setMemberOfBand(userDetails.isMemberOfBand());
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

    @GetMapping(path = "/search/singers")
    public List<UserRest> getSingers() {
        List<UserDTO> foundSingers = userService.findSingers();
        List<UserRest> usersRest = foundSingers.stream()
                .map(e->{
                    UserRest userRest = new UserRest();
                    BeanUtils.copyProperties(e,userRest);
                    return userRest;
                }).collect(Collectors.toList());
        return usersRest;
    }

    @GetMapping(path = "/search/bands")
    @RolesAllowed("user")
    public List<UserRest> getBands() {
        List<UserDTO> foundBands = userService.findBands();
        List<UserRest> usersRest = foundBands.stream()
                .map(e->{
                    UserRest userRest = new UserRest();
                    BeanUtils.copyProperties(e,userRest);
                    return userRest;
                }).collect(Collectors.toList());
        return usersRest;
    }

}
