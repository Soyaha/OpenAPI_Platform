package com.su.suapiclientsdk.model;

import lombok.Data;
/*
*用户
* */
@Data
public class User {
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
