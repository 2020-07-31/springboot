package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class MemberServiceTest {
    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() throws  Exception{
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

    @Test
    public void 중복_회원_예외() throws Exception{
        //given
        Member member1 = new Member();
        member1.setName("spring1");

        Member member2 = new Member();
        member2.setName("spring1");

        //when
        memberService.join(member1);
        //이 로직을 실행할 건데 이 예외가 발생해야한다는 의미
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        // 메세지는 어떻게 검증할까 ctrl alt v
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

      /*  try {
            memberService.join(member2);
            fail();
        }catch (IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo(("이미 존재하는 회원입니다!"));
        }
        */

        //then

    }
}
