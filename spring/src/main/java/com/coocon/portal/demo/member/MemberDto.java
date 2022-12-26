package com.coocon.portal.demo.member;

import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Component
public class MemberDto {
    private String id;
    private String name;

    public void getUserInfo(Member member){
        this.id = member.getId();
        this.name = member.getName();
    }

    public static MemberDto createMemberDtoFromMember(Member member){
        if(member ==null) throw new NullPointerException("user object can't be null");

        return MemberDto.builder().id(member.getId()).name(member.getName()).build();

    }
}
