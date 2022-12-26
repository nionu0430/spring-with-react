package com.coocon.portal.demo.member;

import com.coocon.portal.demo.member.Member;
import com.coocon.portal.demo.member.MemberDto;
import com.coocon.portal.demo.member.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/member")
@Slf4j
public class MemberController {

    private final MemberService memberService;
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/login")
    public ResponseEntity<MemberDto> logIn(@RequestBody Member member){
        log.error("id= [{}], password = [{}]",member.getId(),member.getPassword());

        HttpHeaders headers = new HttpHeaders();
        MemberDto memberDto = new MemberDto();
        if(memberService.isValidMemberLoginData(member.getId(), member.getPassword())){
            memberDto = memberService.getMemberDto(member.getId(), member.getPassword());
        }
        else {
            log.error("failed to find member!");
            throw new NullPointerException("invalid login info");
        }

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(memberDto);
    }

    @GetMapping("/members")
    public ResponseEntity<List<Member>> getMemberData(){
        HttpHeaders headers = new HttpHeaders();
        MemberDto memberDto = new MemberDto();


        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(memberService.getMembers());
    }


    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> handleNullPointerException(NullPointerException nullPointerException){
        nullPointerException.printStackTrace();

        Map<String, String> error = new HashMap<>();
        error.put("code", "ERR5001");
        error.put("message", "Required request body is missing");

        log.debug("nullPointerException.message = {}",nullPointerException.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(nullPointerException.getMessage());
    }

    @PostMapping("/sign_in")
    public void signIn(@RequestBody Member member){
        log.debug("Member = {}",member);
    }
}
