package hello.core.member;

public class MemberServiceImpl implements MemberService {

    // cmd + shift + enter : 전체완성
    // 인터페이스 = 구현체 : memberRepository호출시 MemoryMemberRepository로 가서 연결된 구현체의 함수 호출 (다형성)
    private final MemberRepository memberRepository;

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
}
