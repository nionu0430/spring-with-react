package com.coocon.portal.demo.member;


import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name ="MEMBER",
        uniqueConstraints=
    @UniqueConstraint(columnNames={"id"})
)
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Member {

    @Id
    @NotNull
    private String id;
    @NotNull
    private String password;
    @NotNull
    private String name;

    @Builder
    Member(String id, String password, String name ){
        this.id = id;
        this.password = password;
        this.name = name;
    }

    public boolean checkPassword(String password){
        return this.getPassword().equals(password);
    }
}
