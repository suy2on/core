package hello.core.singleton;

//싱글톤
public class SingletonService {
    //하나만 만들어짐
    private static final SingletonService instance = new SingletonService();

    // 이 메서드로만 SingletonService 객체 받을 수 있음
    public static SingletonService getInstance() {
        return instance;
    }

    // 외부에서 new 할 수 없도록
    private SingletonService(){

    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }

}
