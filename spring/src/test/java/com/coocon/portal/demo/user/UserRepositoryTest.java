package com.coocon.portal.demo.user;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @BeforeEach
    private void insert_basic_user() {
        User user = User.builder().id("test").name("er").password("tteesstt").build();
        userRepository.save(user);
    }

    @Test
    //@DisplayName("유저 삽입 후 검색")
    public void insert_then_select_user(){
        User user = User.builder().id("init_user").name("dd").password("coocon123!").build();
        userRepository.save(user);

        assertNotNull(userRepository.findById("init_user"));
    }

    @Test
    public void select_a_user(){
        assertTrue(userRepository.findById("test").isPresent());
    }

    @Test
    public void update_user(){
        Optional<User> test = userRepository.findById("test");

        test.ifPresent(user -> user.setPassword("changed"));
        userRepository.save(test.get());

        assertEquals("changed", userRepository.findById("test").get().getPassword());
    }

    @Test
    public void delete_user(){
        userRepository.deleteById("test");
        assertFalse(userRepository.findById("test").isPresent());
    }

    @Test
    public void select_all_user(){
        User user = User.builder().id("init_user").name("dd").password("coocon123!").build();
        User user2 = User.builder().id("init_user").name("dd2").password("coocon123!").build();
        User user3 = User.builder().id("init_user2").name("dd3").password("coocon123!").build();

        userRepository.save(user);
        userRepository.save(user2);
        userRepository.save(user3);

        user = User.builder().id("init_user4").name("dd").password("coocon123!").build();
        userRepository.save(user);

        List<User> test = userRepository.findAll();
        test.stream().forEach(System.out::println);

        assertEquals(4,test.size());
    }
}