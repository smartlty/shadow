package com.denachina.shadow.pojo;

import lombok.Data;

import java.time.ZonedDateTime;
import java.util.Date;

@Data
public class SysUser {

    private Integer userId;

    private String username;

    private String password;

    private String nickname;

    private Short deleteStatus;

    private String permissions;

    private String extra;

    private Date createTime;

    private Date updateTime;

    public SysUser(){
    }
}
