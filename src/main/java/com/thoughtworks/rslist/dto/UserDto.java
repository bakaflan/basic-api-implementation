package com.thoughtworks.rslist.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.thoughtworks.rslist.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;

import javax.persistence.Entity;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @Id
    @GeneratedValue
    private int Id;
    @Column(name = "name")
    private String userName;
    private String gender;
    private int age;
    private String email;
    private String phone;
    private int voteNum;

    @OneToMany
    private List<RsDto> rsDtoList = new ArrayList<>();

    public static UserDto bind(User user){
        return UserDto.builder()
                .userName(user.getUserName())
                .gender(user.getGender())
                .age(user.getAge())
                .email(user.getEmail())
                .phone(user.getPhone())
                .voteNum(user.getVoteNum()).build();
    }

    public static User parse(UserDto userDto){
        return User.builder()
                .userName(userDto.getUserName())
                .gender(userDto.getGender())
                .age(userDto.getAge())
                .email(userDto.getEmail())
                .phone(userDto.getPhone())
                .voteNum(userDto.getVoteNum()).build();
    }

    public void vote(Integer voteNum){
        this.voteNum -= voteNum;
    }

}
