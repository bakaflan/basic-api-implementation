package com.thoughtworks.rslist.apitest;

import com.thoughtworks.rslist.controller.RsController;
import com.thoughtworks.rslist.service.RsService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RsControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private RsService rsService;

//    @BeforeAll
//    void setUp(){
//
//    }
    @AfterEach
    void backup(){
        rsService.initRsList();
    }

    @Test
    @Order(0)
    void shouldd_return_re_List() throws Exception {
        mockMvc.perform(get("/rs/list"))
                .andExpect(jsonPath("$",hasSize(3)))
                .andExpect(jsonPath("$[0].eventName",is("第一条事件")))
                .andExpect(status().isOk());
    }

    @Test
    @Order(1)
    void should_get_rs_by_index_of_rs() throws Exception {
        mockMvc.perform(get("/rs")
                .param("index","0"))
                .andExpect(content().string("第一条事件"))
                .andExpect(status().isOk());
    }

    @Test
    @Order(2)
    void should_return_rs_list_by_range() throws Exception {
        mockMvc.perform(get("/rs/list")
                .param("start","0")
                .param("end","2"))
                .andExpect(jsonPath("$",hasSize(2)))
                .andExpect(jsonPath("$[1].eventName",is("第二条事件")))
                .andExpect(status().isOk());
    }

    @Test
    @Order(3)
    void should_add_rs_with_keyword_and_eventName() throws Exception {
        //language=JSON
        String requestJson = "{\n" +
                "  \"keyword\":\"这是一个关键字\",\n" +
                "  \"eventName\":\"这是一个事件\"\n" +
                "}";

        mockMvc.perform(post("/rs")
                .contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(content().string("成功添加"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/rs/list"))
                .andExpect(jsonPath("$",hasSize(4)))
                .andExpect(jsonPath("$[3].eventName",is("这是一个事件")))
                .andExpect(status().isOk());

    }

    @Test
    @Order(4)
    void should_update_rs_with_index_and_keyword_and_eventName() throws Exception {
        //language=JSON
        String requestJson = "{\n" +
                "  \"index\": 0,\n" +
                "  \"keyword\":\"事件一改\",\n" +
                "  \"eventName\":\"第一条事件改\"\n" +
                "}";
        //language=JSON
        String requestJson1 = "{\n" +
                "  \"index\": 1,\n" +
                "  \"keyword\":\"事件二改\"\n" +
                "}";

        mockMvc.perform(patch("/rs")
                .contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(content().string("成功更新"))
                .andExpect(status().isOk());

        mockMvc.perform(patch("/rs")
                .contentType(MediaType.APPLICATION_JSON).content(requestJson1))
                .andExpect(content().string("成功更新"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/rs/list"))
                .andExpect(jsonPath("$",hasSize(3)))
                .andExpect(jsonPath("$[0].eventName",is("第一条事件改")))
                .andExpect(jsonPath("$[1].keyword",is("事件二改")))
                .andExpect(status().isOk());

    }

    @Test
    @Order(5)
    void should_delete_rs_with_index() throws Exception {
        mockMvc.perform(delete("/rs")
                .param("index","1"))
                .andExpect(content().string("成功删除"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/rs/list"))
                .andExpect(jsonPath("$",hasSize(2)))
                .andExpect(jsonPath("$[0].eventName",is("第一条事件")))
                .andExpect(jsonPath("$[1].eventName",is("第三条事件")))
                .andExpect(status().isOk());

    }


}
