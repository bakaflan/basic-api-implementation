package com.thoughtworks.rslist.service;

import com.thoughtworks.rslist.Exception.UserNotExistedException;
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

    @Autowired
    UserRepository userRepository;

    public UserService(){

    }

    @Transactional
    public UserDto creatUser(User user){
        UserDto dto = userRepository.save(UserDto.bind(user));
        return dto;
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

    @Transactional
    public void deleteUserById(Integer id){
        userRepository.deleteById(id);
    }

    public void existUserById(Integer id){
        userRepository.existsById(id);
    }
}
