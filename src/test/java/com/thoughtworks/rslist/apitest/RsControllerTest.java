package com.thoughtworks.rslist.apitest;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
    void should_get_rs_by_index_of_rs() throws Exception {
        mockMvc.perform(get("/rs/index")
                .param("index","0"))
                .andExpect(content().string("第一条事件"))
                .andExpect(status().isOk());
    }

    @Test
    void should_return_rs_list_by_range() throws Exception {
        mockMvc.perform(get("/rs/range")
                .param("start","0")
                .param("end","2"))
                .andExpect(content().string("[第一条事件, 第二条事件]"))
                .andExpect(status().isOk());
    }

    @Test
    void should_add_rs_with_keyword_and_eventName() throws Exception {
        //language=JSON
        String requestJson = "{\n" +
                "  \"keyword\":\"这是一个关键字\",\n" +
                "  \"eventName\":\"这是一个事件\"\n" +
                "}";

        mockMvc.perform(post("/rs/add")
                .contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(content().string("成功添加"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/rs/list"))
                .andExpect(content().string("[第一条事件, 第二条事件, 第三条事件, 这是一个事件]"))
                .andExpect(status().isOk());

    }


}
