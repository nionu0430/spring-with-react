package com.coocon.portal.demo.user;

import com.coocon.portal.demo.Util.JsonUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;

//@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
class UserControllerTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    private void insert_basic_user() {
        User user = User.builder().id("test").name("er").password("test1234").build();
        userRepository.save(user);
    }
    @Test
    public void login_success_test() throws Exception {
        User user = User.builder().id("test").password("test1234").name("name").build();

        System.out.println(user.toString());
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/user/login")
                .contentType("application/json")
                .content(JsonUtil.makeJsonStringByObject(user)).accept(MediaType.ALL)
        ).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
    }


    @Test
    public void get_users_rescdoc() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user/users")
                .accept(MediaType.ALL))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(document("members_get"));
    }
    @Test
    public void login_fail_test() throws Exception {
        User user = User.builder().id("user").password("test1234").name("name").build();
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/user/login")
                .contentType("application/json")
                .content(JsonUtil.makeJsonStringByObject(user)).accept(MediaType.ALL)
        ).andDo(MockMvcResultHandlers.print()).
                andExpectAll(MockMvcResultMatchers.status().is4xxClientError()).andReturn();
    }

}