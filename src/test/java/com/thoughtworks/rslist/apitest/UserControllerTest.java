package com.thoughtworks.rslist.apitest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.rslist.pojo.User;
import com.thoughtworks.rslist.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserService userService;

    @AfterEach
    void backup(){
        userService.initUserList();
    }

    @Test
    public void should_register_user() throws Exception {
        User user = new User("xiaowang","female",20,"123@thoughtworks.com","18988888888");
        String jsonString = objectMapper.writeValueAsString(user);
        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON).content(jsonString))
                .andExpect(content().string("成功新增用户"))
                .andExpect(status().isOk());
    }

    @Test
    public void should_get_user_by_userName() throws Exception {
        User user = new User("xiaowang","female",20,"123@thoughtworks.com","18988888888");
        String jsonString = objectMapper.writeValueAsString(user);
        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON).content(jsonString))
                .andExpect(content().string("成功新增用户"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/user").param("userName","xiaowang"))
                .andExpect(jsonPath("userName",is("xiaowang")))
                .andExpect(jsonPath("denger",is("female")))
                .andExpect(status().isOk());
    }

    @Test
    public void should_get_user_list() throws Exception {
        User user = new User("xiaowang","female",20,"123@thoughtworks.com","18988888888");
        String jsonString = objectMapper.writeValueAsString(user);
        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON).content(jsonString))
                .andExpect(content().string("成功新增用户"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/user/list"))
                .andExpect(jsonPath("$",hasSize(1)))
                .andExpect(jsonPath("$[0].userName",is("xiaowang")))
                .andExpect(status().isOk());
    }

    @Test
    public void should_not_add_user_after_validate() throws Exception {
        User user = new User(null,"female",20,"123@thoughtworks.com","18988888888");
        String jsonString = objectMapper.writeValueAsString(user);
        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON).content(jsonString))
                .andExpect(status().isBadRequest());

        user = new User("xiaowangasdfasdfasdf","female",20,"123@thoughtworks.com","18988888888");
        jsonString = objectMapper.writeValueAsString(user);
        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON).content(jsonString))
                .andExpect(status().isBadRequest());

        user = new User("xiaowang",null,20,"123@thoughtworks.com","18988888888");
        jsonString = objectMapper.writeValueAsString(user);
        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON).content(jsonString))
                .andExpect(status().isBadRequest());

        user = new User("xiaowang","female",10,"123@thoughtworks.com","18988888888");
        jsonString = objectMapper.writeValueAsString(user);
        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON).content(jsonString))
                .andExpect(status().isBadRequest());

        user = new User("xiaowang","female",101,"123@thoughtworks.com","18988888888");
        jsonString = objectMapper.writeValueAsString(user);
        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON).content(jsonString))
                .andExpect(status().isBadRequest());

        user = new User("xiaowang","female",20,"thoughtworks.com","18988888888");
        jsonString = objectMapper.writeValueAsString(user);
        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON).content(jsonString))
                .andExpect(status().isBadRequest());

        user = new User("xiaowang","female",20,"123@thoughtworks.com","18988");
        jsonString = objectMapper.writeValueAsString(user);
        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON).content(jsonString))
                .andExpect(status().isBadRequest());


    }

}
