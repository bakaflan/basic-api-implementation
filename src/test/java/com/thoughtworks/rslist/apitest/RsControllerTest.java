package com.thoughtworks.rslist.apitest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.rslist.controller.RsController;
import com.thoughtworks.rslist.dto.RsDto;
import com.thoughtworks.rslist.dto.UserDto;
import com.thoughtworks.rslist.pojo.Rs;
import com.thoughtworks.rslist.pojo.User;
import com.thoughtworks.rslist.repository.RsRepository;
import com.thoughtworks.rslist.repository.UserRepository;
import com.thoughtworks.rslist.repository.VoteRepository;
import com.thoughtworks.rslist.service.RsService;
import com.thoughtworks.rslist.service.UserService;
import com.thoughtworks.rslist.util.AddRsRequest;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RsControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private RsService rsService;
    @Autowired
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    RsRepository rsRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    VoteRepository voteRepository;

    @BeforeAll
    void init(){

    }
    @BeforeEach
    void setUp(){
        userRepository.deleteAll();
        rsRepository.deleteAll();
        voteRepository.deleteAll();

        rsRepository.initAutoIncrement();
        userRepository.initAutoIncrement();


        User user = new User("xiaowang","female",20,"123@thoughtworks.com","18988888888");
        userRepository.save(UserDto.bind(user));

        Rs rs = new Rs("事件一","第一条事件",1);
        rsRepository.save(RsDto.bind(rs));
    }

    @Test
    void shouldd_return_re_List() throws Exception {
        mockMvc.perform(get("/rs/list"))
                .andExpect(jsonPath("$",hasSize(1)))
                .andExpect(jsonPath("$[0].eventName",is("第一条事件")))
                .andExpect(status().isOk());
    }

    @Test
    void should_get_rs_by_id() throws Exception {
        mockMvc.perform(get("/rs")
                .param("id","1"))
                .andExpect(jsonPath("$.eventName",is("第一条事件")))
                .andExpect(jsonPath("$.keyword",is("事件一")))
                .andExpect(jsonPath("$.id",is(1)))
                .andExpect(jsonPath("$.voteNum",is(0)))
                .andExpect(status().isOk());
    }
//TODO
//    @Test
//    @Order(2)
//    void should_return_rs_list_by_range() throws Exception {
//        mockMvc.perform(get("/rs/list")
//                .param("start","0")
//                .param("end","2"))
//                .andExpect(jsonPath("$",hasSize(1)))
//                .andExpect(jsonPath("$[1].eventName",is("第二条事件")))
//                .andExpect(status().isOk());
//    }

    @Test
    void should_add_rs_with_keyword_and_eventName() throws Exception {
        //language=JSON
        String requestJson = "{\n" +
                "  \"keyword\":\"这是一个关键字\",\n" +
                "  \"eventName\":\"这是一个事件\",\n" +
                "  \"userId\": 1\n" +
                "}";

        mockMvc.perform(post("/rs")
                .contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(header().string("index",is("2")))
                .andExpect(jsonPath("$.eventName",is("这是一个事件")))
                .andExpect(status().isCreated());

        mockMvc.perform(get("/rs/list"))
                .andExpect(jsonPath("$",hasSize(2)))
                .andExpect(jsonPath("$[1].eventName",is("这是一个事件")))
                .andExpect(status().isOk());
    }

    @Test
    void should_update_rs_with_userId_and_keyword_and_eventName() throws Exception {
        //language=JSON
        String requestJson = "{\n" +
                "  \"keyword\":\"事件一改\",\n" +
                "  \"eventName\":\"第一条事件改\",\n" +
                "  \"userId\": 1\n" +
                "}";

        String response = mockMvc.perform(patch("/rs/1")
                .contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(jsonPath("$.eventName",is("第一条事件改")))
                .andExpect(status().isAccepted())
                .andReturn().getResponse().getContentAsString();

        RsDto rsDto = objectMapper.readValue(response,RsDto.class);

        mockMvc.perform(get("/rs/list"))
                .andExpect(jsonPath("$",hasSize(1)))
                .andExpect(jsonPath("$[0].eventName",is("第一条事件改")))
                .andExpect(status().isOk());

    }

    @Test
    @Order(4)
    void should_not_update_rs_with_wrong_userId_and_keyword_and_eventName() throws Exception {
        //language=JSON
        String requestJson = "{\n" +
                "  \"keyword\":\"事件一改\",\n" +
                "  \"eventName\":\"第一条事件改\",\n" +
                "  \"userId\": 2\n" +
                "}";

        mockMvc.perform(patch("/rs/1")
                .contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(jsonPath("$.message",is("rs is not belong to this user")))
                .andExpect(status().isBadRequest());

    }

    @Test
    @Order(5)
    void should_delete_rs_with_index() throws Exception {
        mockMvc.perform(delete("/rs")
                .param("id","1"))
                .andExpect(content().string("成功删除"))
                .andExpect(status().isAccepted());

        mockMvc.perform(get("/rs/list"))
                .andExpect(jsonPath("$",hasSize(0)))
                .andExpect(status().isOk());

        mockMvc.perform(delete("/rs")
                .param("id","1"))
                .andExpect(jsonPath("$.message",is("rs id is not existed")))
                .andExpect(status().isBadRequest());

    }

    @Test
    void should_add_rs_with_user() throws Exception {
        String eventName = "添加一条热搜";
        String keyword = "娱乐";
        AddRsRequest addRsRequest = new AddRsRequest(eventName,keyword,1);
        String jsonString = objectMapper.writeValueAsString(addRsRequest);
        mockMvc.perform(post("/rs")
                .contentType(MediaType.APPLICATION_JSON).content(jsonString))
                .andExpect(status().isCreated());

        mockMvc.perform(get("/rs/list"))
                .andExpect(jsonPath("$",hasSize(2)))
                .andExpect(status().isOk());

        mockMvc.perform(get("/user").param("userName","xiaowang"))
                .andExpect(jsonPath("user_name",is("xiaowang")))
                .andExpect(status().isOk());
    }

    @Test
    void should_add_rs_with_exist_user() throws Exception {
        String eventName = "添加一条热搜";
        String keyword = "娱乐";
        AddRsRequest addRsRequest = new AddRsRequest(eventName,keyword,1);
        String jsonString = objectMapper.writeValueAsString(addRsRequest);
        mockMvc.perform(post("/rs")
                .contentType(MediaType.APPLICATION_JSON).content(jsonString))
                .andExpect(header().string("index","2"))
                .andExpect(status().isCreated());

        addRsRequest.setEventName("添加第二条热搜");
        jsonString = objectMapper.writeValueAsString(addRsRequest);
        mockMvc.perform(post("/rs")
                .contentType(MediaType.APPLICATION_JSON).content(jsonString))
                .andExpect(header().string("index","3"))
                .andExpect(status().isCreated());

        mockMvc.perform(get("/rs/list"))
                .andExpect(jsonPath("$",hasSize(3)))
                .andExpect(status().isOk());

        mockMvc.perform(get("/user/list"))
                .andExpect(jsonPath("$",hasSize(1)))
                .andExpect(jsonPath("$[0].user_name",is("xiaowang")))
                .andExpect(status().isOk());
    }

    @Test
    void should_not_add_rs_after_validate() throws Exception {
        String eventName = "添加一条热搜";
        String keyword = "娱乐";
        AddRsRequest addRsRequest = new AddRsRequest(eventName, keyword, 1);
        String jsonString = objectMapper.writeValueAsString(addRsRequest);

        addRsRequest.setEventName(null);
        jsonString = objectMapper.writeValueAsString(addRsRequest);
        mockMvc.perform(post("/rs")
                .contentType(MediaType.APPLICATION_JSON).content(jsonString))
                .andExpect(jsonPath("$.message",is("invalid param")))
                .andExpect(status().isBadRequest());

        addRsRequest.setKeyword(null);
        addRsRequest.setEventName(eventName);
        jsonString = objectMapper.writeValueAsString(addRsRequest);
        mockMvc.perform(post("/rs")
                .contentType(MediaType.APPLICATION_JSON).content(jsonString))
                .andExpect(jsonPath("$.message",is("invalid param")))
                .andExpect(status().isBadRequest());

    }


    @Test
    void should_vote_rs_with_existed_userId() throws Exception {
        //language=JSON
        String jsonString = "{\n" +
                "  \"voteNum\":5,\n" +
                "  \"userId\": 1\n" +
                "}";
        mockMvc.perform(post("/rs/vote/1")
                .contentType(MediaType.APPLICATION_JSON).content(jsonString))
                .andExpect(status().isAccepted());


    }


    @Test
    void should_not_vote_rs_if_user_voteNum_less_than_votes() throws Exception {
        //language=JSON
        String jsonString = "{\n" +
                "  \"voteNum\":12,\n" +
                "  \"userId\": 1\n" +
                "}";
        mockMvc.perform(post("/rs/vote/1")
                .contentType(MediaType.APPLICATION_JSON).content(jsonString))
                .andExpect(jsonPath("$.message",is("has not enough num of vote")))
                .andExpect(status().isBadRequest());
    }

}
