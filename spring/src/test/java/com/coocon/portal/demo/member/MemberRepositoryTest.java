package com.coocon.portal.demo.member;


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
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @BeforeEach
    private void insert_basic_user() {
        Member member = Member.builder().id("test").name("er").password("tteesstt").build();
        memberRepository.save(member);
    }

    @Test
    //@DisplayName("유저 삽입 후 검색")
    public void insert_then_select_user(){
        Member member = Member.builder().id("init_user").name("dd").password("coocon123!").build();
        memberRepository.save(member);

        assertNotNull(memberRepository.findById("init_user"));
    }

    @Test
    public void select_a_user(){
        assertTrue(memberRepository.findById("test").isPresent());
    }

    @Test
    public void select_invalid_user(){
        assertFalse(memberRepository.findById("test2").isPresent());
    }

    @Test
    public void update_user(){
        Optional<Member> test = memberRepository.findById("test");

        test.ifPresent(user -> user.setPassword("changed"));
        memberRepository.save(test.get());

        assertEquals("changed", memberRepository.findById("test").get().getPassword());
    }

    @Test
    public void delete_user(){
        memberRepository.deleteById("test");
        assertFalse(memberRepository.findById("test").isPresent());
    }

    @Test
    public void select_all_user(){
        Member member = Member.builder().id("init_user").name("dd").password("coocon123!").build();
        Member member2 = Member.builder().id("init_user").name("dd2").password("coocon123!").build();
        Member member3 = Member.builder().id("init_user2").name("dd3").password("coocon123!").build();

        memberRepository.save(member);
        memberRepository.save(member2);
        memberRepository.save(member3);

        member = Member.builder().id("init_user4").name("dd").password("coocon123!").build();
        memberRepository.save(member);

        List<Member> test = memberRepository.findAll();
        test.stream().forEach(System.out::println);

        assertEquals(4,test.size());
    }
}