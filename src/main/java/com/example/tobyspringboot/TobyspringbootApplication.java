package com.example.tobyspringboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration
@ComponentScan(basePackages = {"com.example.tobyspringboot"})
public class TobyspringbootApplication {

    public static void main(String[] args){
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext() {
            @Override
            protected void onRefresh(){
                super.onRefresh();

                ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
                WebServer webServer = serverFactory.getWebServer(servletContext->{
                    servletContext.addServlet("dispatcherServlet",
                            // DispatcherServlet에는 Web용 ApplicationContext를 파라미터로 제공해야한다.
                            new DispatcherServlet(this)
                    ).addMapping("/*");

                });
                webServer.start();
            }
        };
        applicationContext.register(TobyspringbootApplication.class);
        applicationContext.refresh();
    }

}
