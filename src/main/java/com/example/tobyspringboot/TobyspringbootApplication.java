package com.example.tobyspringboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class TobyspringbootApplication {

    public static void main(String[] args){
        GenericWebApplicationContext applicationContext = new GenericWebApplicationContext();
        applicationContext.registerBean(HelloController.class);
        // 인터페이스 타입이 아닌 구체클래스 타입을 명시해야 한다.
        applicationContext.registerBean(SimpleHelloService.class);
        applicationContext.refresh();

        ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
        WebServer webServer = serverFactory.getWebServer(servletContext->{
            servletContext.addServlet("dispatcherServlet",
                    // DispatcherServlet에는 Web용 ApplicationContext를 파라미터로 제공해야한다.
                    new DispatcherServlet(applicationContext)
            ).addMapping("/*");

        });
        webServer.start();
    }

}
