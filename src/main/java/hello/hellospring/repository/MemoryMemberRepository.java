package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence); //id는 시스템이 정해주는 것 !
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny(); // 하나라도 찾으면 리턴 아니면 null
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());//member들이 반환 됨
    }//동작하는지 확인-> 테스트 케이스를 작성해보면됨 !

    public void clearStore(){
        store.clear();
    }
}
