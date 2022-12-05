package com.coocon.portal.demo.user;


import com.sun.istack.NotNull;
import lombok.*;

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

    @GeneratedValue
    private int seq;

    @Builder
    User (String id, String password, String name ){
        this.id = id;
        this.password = password;
        this.name = name;
    }
}
