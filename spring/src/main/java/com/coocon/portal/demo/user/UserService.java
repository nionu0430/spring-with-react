package com.coocon.portal.demo.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public boolean isValidUserLoginData(String id, String password){
        Optional<User> resultUser = userRepository.findById(id);

        if(resultUser.isEmpty()){
            log.error("user result is empty");
            return false;
        }
        else{
            return resultUser.get().checkPassword(password);
        }
    }

    public UserDto getUserDto(String id, String password){
        UserDto userDto = new UserDto();

        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            return UserDto.createUserDtoFromUser(user.get());
        }
        else{
            return null;
        }
    }

    public void logOut(){

    }

    public void signIn(String id, String password, String name){

        User user = User.builder().id(id).password(password).name(name).build();
        userRepository.save(user);
        /*
        if(!userRepository.findById(id).isEmpty()){
            throw Duplicate
        }
        */
    }
}
