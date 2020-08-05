package com.thoughtworks.rslist.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.stereotype.Component;

import javax.validation.constraints.*;

@Component
@Data
@NoArgsConstructor
public class User {
    @NotNull
    @Size(max = 8)
    private String userName;
    @NotNull
    private String denger;
    @NotNull
    @Min(value = 18)
    @Max(value = 100)
    private int age;
    @Email
    private String email;
    @Pattern(regexp = "1\\d{10}")
    private String phone;
    @Value(value = "10")
    private int voteNum;

    public User(String userName, @NotNull String denger, @NotNull @Size(min = 18, max = 100) int age, @Email String email, @Pattern(regexp = "1\\d{10}") String phone) {
        this.userName = userName;
        this.denger = denger;
        this.age = age;
        this.email = email;
        this.phone = phone;
    }
}
