package com.su.suapiinterface;

import com.su.suapiclientsdk.client.SuApiClient;

import com.su.suapiclientsdk.model.User;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SuapiInterfaceApplicationTests {

    @Resource
    private SuApiClient suApiClient;

    @Test
    void contextLoads() {
        String res=suApiClient.getNameByGet("su");
        User user =new User();
        user.setUsername("suyihan");
        String usernameByPost =suApiClient.getNameByPostWithJson(user);
        System.out.println(res);
        System.out.println(usernameByPost);
    }

}
