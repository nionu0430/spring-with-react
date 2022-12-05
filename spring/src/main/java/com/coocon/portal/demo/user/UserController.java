package com.coocon.portal.demo.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/user")
@Slf4j
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> logIn(@RequestBody String id, @RequestBody String password){
        log.debug("id= [{}], password = [{}]",id,password);

        HttpHeaders headers = new HttpHeaders();
        HttpStatus responseStatus = HttpStatus.OK;

        UserDto userDto = userService.logIn(id, password);
        if(userDto == null){
            throw new NullPointerException("invalid login info");
        }

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(userDto);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> handleNullPointerException(NullPointerException nullPointerException){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(nullPointerException.getMessage());
    }

    @PostMapping("/sign_in")
    public void signIn(@RequestBody User user){
        log.debug("User = {}",user);

    }
}
