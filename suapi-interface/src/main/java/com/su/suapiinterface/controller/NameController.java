package com.su.suapiinterface.controller;



import com.su.suapiclientsdk.model.User;
import com.su.suapiclientsdk.utils.SignUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;


/*
* 名称api
*
* @author su
* */
@RestController
@RequestMapping("/name")
public class NameController {
    @GetMapping("/{name}")
    public String getNameByGet(String name) {

        return "发送GET请求 你的名字是：" + name;
    }

    @PostMapping()
    public String getNameByPost(@RequestParam(value = "name") String name) {
        return "发送POST请求 你的名字是：" + name;
    }

    @PostMapping("/user")
    public String getNameByPostWithJson(@RequestBody User user, HttpServletRequest request) {
        String accessKey=request.getHeader("accessKey");
        String nonce=request.getHeader("nonce");
        String timestamp=request.getHeader("timestamp");
        String sign=request.getHeader("sign");
        String body=request.getHeader("body");
        //todo 实际情况中应该从数据库中查是否已经分配给用户
        if(!accessKey.equals("su")) {
            throw new RuntimeException("无权限");
        }
        if(Long.parseLong(nonce)>10000) {
            throw new RuntimeException("无权限");
        }
        //todo 时间不能超过一定范围
        //        if(timestamp){
//
//        }
        //todo 际情况中应该从数据库中查出secretKey
        String serverSign = SignUtils.genSign(body,"123");
        if(!sign.equals(serverSign)) {
            throw new RuntimeException("无权限");
        }
        return "发送POST请求 JSON中你的名字是：" + user.getUsername();
    }
}
