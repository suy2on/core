package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService {

    // cmd + shift + enter : 전체완성
    // 인터페이스 = 구현체 : memberRepository호출시 MemoryMemberRepository로 가서 연결된 구현체의 함수 호출 (다형성)
    private final MemberRepository memberRepository;

    @Autowired // MemberRepository에 맞는 스프링빈을 찾아서 의존성 주입
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);

    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //테스트용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
