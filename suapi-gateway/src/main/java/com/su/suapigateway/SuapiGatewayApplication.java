package com.su.suapigateway;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.stereotype.Service;

@SpringBootApplication
@Service
@EnableDubbo
public class SuapiGatewayApplication {

    @DubboReference
    private DemoService demoService;

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SuapiGatewayApplication.class,args);
        SuapiGatewayApplication application = context.getBean(SuapiGatewayApplication.class);
        String result = application.doSayHello("world");
        String result2 = application.doSayHello2("world");
        System.out.println("result:"+ result);
        System.out.println("result: " + result2);
    }

    public String doSayHello(String name){
        return demoService.sayHello(name);
    }
    public String doSayHello2(String name){
        return demoService.sayHello2(name);
    }

}
