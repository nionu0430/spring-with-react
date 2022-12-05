package com.coocon.portal.demo.user;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto logIn(String id, String password){
        UserDto userDto = null;
        userRepository.findById("id").ifPresent(user->userDto.getUserInfo(user));
        return userDto;
    }

    public void logOut(){

    }

    public void signIn(){

    }
}
