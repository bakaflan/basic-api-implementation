package com.thoughtworks.rslist.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;

import javax.persistence.Entity;
import javax.validation.constraints.*;

@Entity
@Table(name = "user")
@Data
@Builder
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

}
