package com.coocon.portal.demo.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Test
    public void login_success_test(){
        //given //when
        User user = User.builder().name("테스터").id("test").password("test1234!").build();
        doReturn(Optional.of(user)).when(userRepository).findById("test");

        //then
        assertTrue(userService.isValidUserLoginData("test","test1234!"));
    }

    @Test
    public void login_fail_invalid_id(){
        //given //when
        doReturn(Optional.empty()).when(userRepository).findById("test");
        //then
        assertFalse(userService.isValidUserLoginData("test","test1234!"));
    }

    @Test
    public void login_fail_invalid_password(){
        //given //when
        User user = User.builder().name("테스터").id("test").password("test4321!").build();
        doReturn(Optional.of(user)).when(userRepository).findById("test");
        //then
        assertFalse(userService.isValidUserLoginData("test","test1234!"));
    }

    @Test
    public void getUserDto_exist_user(){
        //given //when
        User user = User.builder().name("테스터").id("test").password("test1234").build();
        doReturn(Optional.of(user)).when(userRepository).findById("test");

        //then
        assertFalse(userService.isValidUserLoginData("test","test1234!"));
    }

    @Test
    public void getUserDto_non_exist_user(){
        //given //when
        doReturn(Optional.empty()).when(userRepository).findById("test");
        //then
        assertFalse(userService.isValidUserLoginData("test","test1234!"));
    }
}