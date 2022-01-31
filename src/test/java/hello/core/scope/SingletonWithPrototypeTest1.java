package hello.core.scope;

import ch.qos.logback.core.net.server.Client;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

import static org.assertj.core.api.Assertions.*;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind() {

        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        assertThat(prototypeBean2.getCount()).isEqualTo(1);

    }

    @Test
    void singletonClientUsePrototype(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class, ClientBean.class);

        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        assertThat(count2).isEqualTo(1);


    }

    @Scope("singleton")
    static class ClientBean{

        @Autowired
       // private ObjectProvider<PrototypeBean> prototypeBeanProvider;// 대리로 스프링 컨테이너에 요청해서 해당빈을 가져온다, 스프링빈이 등록되어있다
        private Provider<PrototypeBean> prototypeBeanProvider; // 심플하고 스프링에 의존적이지 않음 , 라이브러리 댕겨와야함 javax.inject


        public int logic(){
            PrototypeBean prototypeBean = prototypeBeanProvider.get(); // dependency lookup
//            PrototypeBean prototypeBean = applicationContext.getBean(PrototypeBean.class); // DL
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }

    }

    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;

        @PostConstruct
        public void init(){
            System.out.println("PrototypeBean.init : " + this);
        }

        @PreDestroy
        public void destroy(){
            System.out.println("PrototypeBean.close");
        }

        public void addCount(){
            count++;
        }

        public int getCount() {
            return count;
        }




    }



}
