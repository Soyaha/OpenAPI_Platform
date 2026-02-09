package com.su.suapigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;

@SpringBootApplication
public class SuapiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(SuapiGatewayApplication.class, args);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("to_baidu", r -> r.path("/baidu")
                        .uri("http://www.baidu.com/"))
                .build();
    }
}
