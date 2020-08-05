package com.thoughtworks.rslist.controller;


import com.thoughtworks.rslist.pojo.User;
import com.thoughtworks.rslist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @PostMapping("/user")
    public String registerUser(@RequestBody @Valid User user){
        userService.addUser(user);
        return "成功新增用户";
    }

    @ResponseBody
    @GetMapping("/user")
    public User getUserByUserName(@RequestParam String userName){
        return userService.getUser(userName);
    }

    @ResponseBody
    @GetMapping("/user/list")
    public List<User> getUserList(){
        return userService.getUserList();
    }

}
