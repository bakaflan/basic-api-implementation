package com.thoughtworks.rslist.service;

import com.thoughtworks.rslist.dto.UserDto;
import com.thoughtworks.rslist.pojo.User;
import com.thoughtworks.rslist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    List<User> userList;

    @Autowired
    UserRepository userRepository;

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

    public int addUserReturnIndex(User user){
        if(!isUserExist(user)){
            this.userList.add(user);
        }
        return userList.indexOf(userList.stream()
                .filter(i->i.getUserName().equals(user.getUserName()))
                .findFirst()
                .get());
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

    @Transactional
    public void creatUser(User user){
        userRepository.save(UserDto.bind(user));
    }

    public User findUserById(Integer id){
        Optional<UserDto> userDtoOptional = userRepository.findById(id);

        return userDtoOptional.map(UserDto::parse).orElse(null);
    }

    public List<User> findUserAll(){
        return userRepository.findAll().stream().map(UserDto::parse).collect(Collectors.toList());
    }

    public User findUserByUserName(String userName){
        Optional<UserDto> userDtoOptional = userRepository.findUserByUserName(userName);
        return userDtoOptional.map(UserDto::parse).orElse(null);
    }

    public void deleteUserById(Integer id){
        userRepository.deleteById(id);
    }
}
