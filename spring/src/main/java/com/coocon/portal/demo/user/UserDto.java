package com.coocon.portal.demo.user;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    private String id;
    private String name;

    public void getUserInfo(User user){
        this.id = user.getId();
        this.name = user.getName();
    }
}
