package com.coocon.portal.demo.user;

import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Component
public class UserDto {
    private String id;
    private String name;

    public void getUserInfo(User user){
        this.id = user.getId();
        this.name = user.getName();
    }

    public static UserDto createUserDtoFromUser(User user){
        if(user==null) throw new NullPointerException("user object can't be null");

        return UserDto.builder().id(user.getId()).name(user.getName()).build();

    }
}
