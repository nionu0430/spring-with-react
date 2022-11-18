package com.coocon.portal.demo.user;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
@Getter
@NoArgsConstructor
public class User {

    @Id
    private String id;

    private String password;
    private String name;
}
