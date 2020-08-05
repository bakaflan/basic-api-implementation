package com.thoughtworks.rslist.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
@Builder
public class User {
    @NotNull
    @Size(max = 8)
    @JsonProperty("user_name")
    private String userName;
    @NotNull
    @JsonProperty("user_gender")
    private String gender;
    @NotNull
    @Min(value = 18)
    @Max(value = 100)
    @JsonProperty("user_age")
    private int age;
    @Email
    @JsonProperty("user_email")
    private String email;
    @Pattern(regexp = "1\\d{10}")
    @JsonProperty("user_phone")
    private String phone;
    @Value(value = "10")
    private int voteNum;

    public User(String userName, @NotNull String gender, @NotNull @Size(min = 18, max = 100) int age, @Email String email, @Pattern(regexp = "1\\d{10}") String phone) {
        this.userName = userName;
        this.gender = gender;
        this.age = age;
        this.email = email;
        this.phone = phone;
    }
}
