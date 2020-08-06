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
        userService.creatUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("保存成功");
    }

    @GetMapping("/user")
    public ResponseEntity findUser(@RequestParam(required = false) Integer id,@RequestParam(required = false) String userName){
        if(id!=null&&userName==null){
            return ResponseEntity.status(HttpStatus.OK).body(userService.findUserById(id));
        }
        if(id==null&&userName!=null){
            return ResponseEntity.status(HttpStatus.OK).body(userService.findUserByUserName(userName));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).header("error","bad request with double param").body("");
    }

    @GetMapping("/user/list")
    public ResponseEntity getUserList(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.findUserAll());
    }

    @DeleteMapping("/user")
    public ResponseEntity deleteUserById(@RequestParam Integer id){
        userService.deleteUserById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("删除成功");
    }



}
