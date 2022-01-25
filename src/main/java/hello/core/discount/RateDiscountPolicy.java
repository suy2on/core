package hello.core.discount;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import jdk.jfr.Percentage;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
//@MainDiscountPolicy //어노테이션 생성  cmd + option + B
public class RateDiscountPolicy implements DiscountPolicy{

    private int discountPercent = 10;

    // cmd + shift + T : 테스트생성
    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP){
            return price * discountPercent /100;
        } else {
            return 0;
        }
    }
}
