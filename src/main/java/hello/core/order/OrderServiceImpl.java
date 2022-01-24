package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository;
    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy(); : DIP 위반 , 추상과 구체에 모두 의존
    //private final DiscountPolicy discountPolicy = new RateDiscountPolicy();  OCP 위반 , 확장하면서 코드를 수정..
    private final DiscountPolicy discountPolicy; // DIP 지켜짐

//    수정자주입 : 선택전, 변경가능
//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository){
//        this.memberRepository = memberRepository;
//    }
//
//    @Autowired
//    public void setDiscountPolicy(DiscountPolicy discountPolicy){
//        this.discountPolicy = discountPolicy;
//    }

    // 생성자 의존성주입 : 불변
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice); // 단일책임원칙 잘지켜짐

        // cmd + p : 파라미터 확인
        return new Order(memberId, itemPrice, itemName, discountPrice);

    }

    //테스트용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
