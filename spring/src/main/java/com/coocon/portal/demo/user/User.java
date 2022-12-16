package com.coocon.portal.demo.user;


import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Entity
@Table(name ="USERS",
        uniqueConstraints=
    @UniqueConstraint(columnNames={"id"})
)
@Getter
@Setter
@NoArgsConstructor
@ToString
public class User {

    @Id
    @NotNull
    private String id;
    @NotNull
    private String password;
    @NotNull
    private String name;

    @Builder
    User (String id, String password, String name ){
        this.id = id;
        this.password = password;
        this.name = name;
    }

    public boolean checkPassword(String password){
        return this.getPassword().equals(password);
    }
}
