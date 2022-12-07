package com.coocon.portal.demo.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public boolean isValidUserLoginData(String id, String password){
        Optional<Member> resultUser = memberRepository.findById(id);

        if(resultUser.isEmpty()){
            log.error("user result is empty");
            return false;
        }
        else{
            return resultUser.get().checkPassword(password);
        }
    }

    public MemberDto getUserDto(String id, String password){
        MemberDto memberDto = new MemberDto();

        Optional<Member> user = memberRepository.findById(id);
        if(user.isPresent()){
            return MemberDto.createUserDtoFromUser(user.get());
        }
        else{
            return null;
        }
    }

    public void logOut(){

    }

    public void signIn(String id, String password, String name){

        Member member = Member.builder().id(id).password(password).name(name).build();
        memberRepository.save(member);
        /*
        if(!userRepository.findById(id).isEmpty()){
            throw Duplicate
        }
        */
    }
}
