package com.coocon.portal.demo.member;

import com.coocon.portal.demo.Util.JsonUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

//@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class MemberControllerTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    private void insert_basic_user() {
        Member member = Member.builder().id("test").name("er").password("test1234").build();
        memberRepository.save(member);
    }
    @Test
    public void login_success_test() throws Exception {
        Member member = Member.builder().id("test").password("test1234").name("name").build();

        System.out.println(member.toString());
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/user/login")
                .contentType("application/json")
                .content(JsonUtil.makeJsonStringByObject(member)).accept(MediaType.ALL)
        ).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
    }

    @Test
    public void login_fail_test() throws Exception {
        Member member = Member.builder().id("user").password("test1234").name("name").build();
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/user/login")
                .contentType("application/json")
                .content(JsonUtil.makeJsonStringByObject(member)).accept(MediaType.ALL)
        ).andDo(MockMvcResultHandlers.print()).
                andExpectAll(MockMvcResultMatchers.status().is4xxClientError()).andReturn();
    }

}