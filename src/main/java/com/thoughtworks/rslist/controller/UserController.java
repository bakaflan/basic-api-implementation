package com.thoughtworks.rslist.controller;


import com.thoughtworks.rslist.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    private List<User> userList = new ArrayList<>();

    @ResponseBody
    @PostMapping("/user")
    public String registerUser(@RequestBody @Valid User user){
        userList.add(user);
        return "成功新增用户";
    }

}
