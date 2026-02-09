package com.su.suapiclientsdk.client;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.su.suapiclientsdk.model.User;


import java.util.HashMap;
import java.util.Map;

import static com.su.suapiclientsdk.utils.SignUtils.genSign;


/*
* 调用第三方接口的客户端
* */
public class SuApiClient {
    private String accessKey;
    private String secretKey;
    private static final String GATEWAY_HOST="http://1ocalhost:8090";
    public SuApiClient(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    public String getNameByGet(String name){
        // 可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
        return HttpUtil.get(GATEWAY_HOST+"/api/name"+name);
    }

    public String getNameByPost( String name){
        // 可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);
        return HttpUtil.post(GATEWAY_HOST+"/api/name", paramMap);
    }
    private Map<String,String> getHeaderMap(String body){
        Map<String,String> hashmap = new HashMap<>();
        hashmap.put("accessKey", accessKey);
        // 不能直接发送给后端
//        hashmap.put("secretKey", secretKey);
        hashmap.put("nonce", RandomUtil.randomNumbers(4));
        hashmap.put("body", body);
        hashmap.put("timestamp", String.valueOf(System.currentTimeMillis()/1000));
        hashmap.put("sign", genSign(body,secretKey));
        return hashmap;
    }


    public String getNameByPostWithJson(User user){
        String json = JSONUtil.toJsonStr(user);
        HttpResponse response = HttpRequest.post(GATEWAY_HOST+"/api/name/user")
                .addHeaders(getHeaderMap(json))
                .body(json)
                .execute();
        System.out.println("response = " + response);
        System.out.println("status = " + response.getStatus());
        if (response.isOk()) {
            return response.body();
        }
        return "fail";
    }
}
