package com.coocon.portal.demo.member;


import com.coocon.portal.demo.authority.Authority;
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
    @Enumerated
    private Authority authority;



    @Builder
    Member(String id, String password, String name, Authority authority ){
        this.id = id;
        this.password = password;
        this.name = name;
        this.authority = authority;
    }

    public boolean checkPassword(String password){
        return this.getPassword().equals(password);
    }
}
