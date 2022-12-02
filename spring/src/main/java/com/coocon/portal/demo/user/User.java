package com.coocon.portal.demo.user;


import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="USERS")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @NotNull
    private String id;
    @NotNull
    private String password;
    @NotNull
    private String name;
    private int seq;

    @Builder
    User (String id, String password, String name ){
        this.id = id;
        this.password = password;
        this.name = name;
    }
}
