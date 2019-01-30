package com.denachina.shadow.pojo;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class SysUser {

    private Integer userId;

    private String username;

    private String password;

    private String nickname;

    private Short deleteStatus;

    private String permissions;

    private String extra;

    private ZonedDateTime createTime;

    private ZonedDateTime updateTime;

    public SysUser(){
    }
}
