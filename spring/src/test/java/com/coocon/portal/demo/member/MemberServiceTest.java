package com.coocon.portal.demo.member;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {
    @InjectMocks
    private MemberService memberService;

    @Mock
    private MemberRepository memberRepository;

    @Test
    public void login_success_test(){
        //given //when
        Member member = Member.builder().name("테스터").id("test").password("test1234!").build();
        doReturn(Optional.of(member)).when(memberRepository).findById("test");

        //then
        assertTrue(memberService.isValidMemberLoginData("test","test1234!"));
    }

    @Test
    public void login_fail_invalid_id(){
        //given //when
        doReturn(Optional.empty()).when(memberRepository).findById("test");
        //then
        assertFalse(memberService.isValidMemberLoginData("test","test1234!"));
    }

    @Test
    public void login_fail_invalid_password(){
        //given //when
        Member member = Member.builder().name("테스터").id("test").password("test4321!").build();
        doReturn(Optional.of(member)).when(memberRepository).findById("test");
        //then
        assertFalse(memberService.isValidMemberLoginData("test","test1234!"));
    }

    @Test
    public void getUserDto_exist_user(){
        //given //when
        Member member = Member.builder().name("테스터").id("test").password("test1234").build();
        doReturn(Optional.of(member)).when(memberRepository).findById("test");

        //then
        assertFalse(memberService.isValidMemberLoginData("test","test1234!"));
    }

    @Test
    public void getUserDto_non_exist_user(){
        //given //when
        doReturn(Optional.empty()).when(memberRepository).findById("test");
        //then
        assertFalse(memberService.isValidMemberLoginData("test","test1234!"));
    }
}