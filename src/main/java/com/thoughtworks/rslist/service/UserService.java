package com.thoughtworks.rslist.service;

import com.thoughtworks.rslist.pojo.User;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    List<User> userList;

    public UserService(){
        this.userList = new ArrayList<>();
    }

    public void initUserList(){
        this.userList = new ArrayList<>();
    }

    public void addUser(User user){
        if(!isUserExist(user)){
            this.userList.add(user);
        }
    }

    public boolean isUserExist(User user){
        Optional<User> optionalUser = userList.stream()
                .filter(i -> i.getUserName().equals(user.getUserName())).findFirst();
        return optionalUser.isPresent();
    }

    public User getUser(String userName) {
        Optional<User> optionalUser = userList.stream()
                .filter(i -> i.getUserName().equals(userName)).findFirst();
        return optionalUser.orElseGet(User::new);
    }

    public List<User> getUserList(){
        return this.userList;
    }
}
