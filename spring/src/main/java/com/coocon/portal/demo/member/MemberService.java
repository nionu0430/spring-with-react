package com.coocon.portal.demo.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<Member> getMembers(){
        return memberRepository.findAll();
    }

    public boolean isValidMemberLoginData(String id, String password){
        Optional<Member> resultMember = memberRepository.findById(id);

        if(resultMember.isEmpty()){
            log.error("member result is empty");
            return false;
        }
        else{
            return resultMember.get().checkPassword(password);
        }
    }

    public MemberDto getMemberDto(String id, String password){
        MemberDto memberDto = new MemberDto();

        Optional<Member> member = memberRepository.findById(id);
        if(member.isPresent()){
            return MemberDto.createMemberDtoFromMember(member.get());
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
        if(!memberRepository.findById(id).isEmpty()){
            throw Duplicate
        }
        */
    }
}
