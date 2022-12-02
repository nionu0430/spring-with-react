package com.coocon.portal.demo.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    User user = User.builder().id("init_user").name("first").password("coocon123!").build();

    @Test
    @DisplayName("신규 유저 삽입 후 검색")
    public void insert_then_select_user(){
        userRepository.save(user);

        User user2 = User.builder().id("init_user").name("dd").password("coocon123!").build();
        userRepository.save(user2);

        assertNotNull(userRepository.findById("init_user"));
    }

    @Test
    public void select_user(){
        User user  = User.builder().id("tester").password("password123!").name("홍길동").build();
        userRepository.save(user);
        System.out.println(user.getId());

        assertTrue(userRepository.findById("tester").isPresent());
    }

    @Test
    public void update_user(){
        Optional<User> test = userRepository.findById("insert_test");
        System.out.println(test.get().getId());

        test.map(User::getPassword).
                ifPresent(System.out::println);

        assertTrue(test.isPresent());

        assertNotNull(userRepository.findById("test"));
    }
}