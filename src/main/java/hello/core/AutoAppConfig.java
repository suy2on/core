package hello.core;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;


@Configuration
@ComponentScan(
        //basePackages = "hello.core.member", // 여기 하위부터 스캔  default는 이 파일 위치 아래로 스캔
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class) // AppConfig제외시켜주기위해
)
public class AutoAppConfig {


}
