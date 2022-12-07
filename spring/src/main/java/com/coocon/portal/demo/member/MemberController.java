package com.coocon.portal.demo.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> logIn(@RequestBody User user){
        log.error("id= [{}], password = [{}]",user.getId(),user.getPassword());

        HttpHeaders headers = new HttpHeaders();
        UserDto userDto = new UserDto();
        if(userService.isValidUserLoginData(user.getId(), user.getPassword())){
            userDto = userService.getUserDto(user.getId(), user.getPassword());
        }
        else {
            log.error("failed to find user!");
            throw new NullPointerException("invalid login info");
        }

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(userDto);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUserData(){
        HttpHeaders headers = new HttpHeaders();
        UserDto userDto = new UserDto();


        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(userService.getUsers());
    }


    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> handleNullPointerException(NullPointerException nullPointerException){
        nullPointerException.printStackTrace();

        Map<String, String> error = new HashMap<>();
        error.put("code", "ERR5001");
        error.put("message", "Required request body is missing");

        log.debug("nullPointerException.message = {}",nullPointerException.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(nullPointerException.getMessage());
    }

    @PostMapping("/sign_in")
    public void signIn(@RequestBody User user){
        log.debug("User = {}",user);
    }
}
