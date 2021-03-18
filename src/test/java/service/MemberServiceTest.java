package service;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

public class MemberServiceTest {
    MemberService memberService = new MemberService();

    @Test
    void 회원가입(){
        //given
        Member member = new Member();
        member.setName("hello");

        //when
        Long savdId = memberService.join(member);

        //then
        //memberService.findOne(savdId); //alt Enter
        Member findMember = memberService.findOne(savdId).get();
        //Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
        // alt Enter
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }
}
