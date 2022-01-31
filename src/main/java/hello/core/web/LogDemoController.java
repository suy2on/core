package hello.core.web;


import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;
    private final MyLogger myLogger; // 실제 request가 왔을 때 주입해줘야한다..! DL을통한 지연
    //private final ObjectProvider<MyLogger> myLoggerProvider; // DL

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request){
        //MyLogger myLogger = myLoggerProvider.getObject();
        String requestURL = request.getRequestURL().toString();
        System.out.println("myLogger = " + myLogger.getClass()); // MyLogger$$EnhancerBySpringCGLIB$$d9f52477
        myLogger.setRequestURL(requestURL); // 프록시가 실제 빈에 접근해서 가져온다

        myLogger.log("controller test");
        logDemoService.logic("testId");
        return "OK";

    }
}
