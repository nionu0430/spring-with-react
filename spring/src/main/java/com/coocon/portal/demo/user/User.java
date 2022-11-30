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
public class TestUser {

    @Id
    private String userId;


    private String userPassword;
    private String userName;

}
