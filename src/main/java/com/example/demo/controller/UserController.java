//package com.example.demo.controller;
//
//import com.example.demo.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/users")
//public class UserController {
//
//    @Autowired
//    UserService userService;
//
//    @GetMapping(path = "/{id}")
//    public UserRest getUser(@PathVariable String id) {
//
//       return  userService.getUserByUserId(id);
//
//
//
//        //return EntityModel.of(returnValue).add(userResourceLink).add(addressesResourceLink);
//    }
//
//}
