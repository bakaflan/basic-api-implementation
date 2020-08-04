package com.thoughtworks.rslist.apitest;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class RsControllerTest {
    @Autowired
    private MockMvc mockMvc;

//    @BeforeAll
//    void setUp(){
//
//    }

    @Test
    void shouldd_return_re_List() throws Exception {
        mockMvc.perform(get("/rs/list"))
                .andExpect(content().string("[第一条事件, 第二条事件, 第三条事件]"))
                .andExpect(status().isOk());
    }

    @Test
    void should_get_rs_with_index_of_rs() throws Exception {
        mockMvc.perform(get("/rs")
                .param("index","0"))
                .andExpect(content().string("第一条事件"))
                .andExpect(status().isOk());
    }

}
