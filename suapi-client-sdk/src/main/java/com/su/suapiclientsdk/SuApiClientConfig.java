package com.su.suapiclientsdk;

import com.su.suapiclientsdk.client.SuApiClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("suapi.client")
@Data
@ComponentScan
public class SuApiClientConfig {
    private String accessKey;

    private String secretKey;

    @Bean
    public SuApiClient suApiClient() {
        return new SuApiClient(accessKey, secretKey);

    }
}
