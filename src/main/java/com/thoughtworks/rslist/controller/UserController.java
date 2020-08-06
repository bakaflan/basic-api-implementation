package com.thoughtworks.rslist.controller;


import com.thoughtworks.rslist.pojo.User;
import com.thoughtworks.rslist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ResponseEntity registerUser(@RequestBody @Valid User user){
        int index = userService.addUserReturnIndex(user);
        return ResponseEntity.status(HttpStatus.CREATED).header("index",String.valueOf(index)).body("");
    }

    @GetMapping("/user")
    public ResponseEntity getUserByUserName(@RequestParam String userName){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUser(userName));
    }

    @GetMapping("/user/list")
    public ResponseEntity getUserList(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserList());
    }

}
