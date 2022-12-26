package com.coocon.portal.demo.authority;


import com.coocon.portal.demo.member.Member;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    public String login(@RequestBody Member member){
        return "test";
    }
}
