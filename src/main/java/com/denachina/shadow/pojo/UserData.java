package com.denachina.shadow.pojo;

import lombok.Data;

import java.time.LocalDate;
import java.time.ZonedDateTime;

@Data
public class UserData {

    private Integer userId;

    private String userName;

    private String sexType;

    private LocalDate birthday;

    private String jobName;

    private Short interest;

    private String intro;

    private Short isFreeze;

    private Short userStatus;

    private Integer level;

    private String email;

    private String phoneNo;

    private ZonedDateTime createdOn;

    private ZonedDateTime updatedOn;

    public UserData(){}

    public UserData(String userName,String sexType, LocalDate birthday,String jobName,String intro,Integer level,String email,String phoneNo){
        this.userName = userName;
        this.sexType = sexType;
        this.birthday = birthday;
        this.jobName = jobName;
        this.interest = 0;
        this.intro = intro;
        this.isFreeze = 0;
        this.userStatus = 0;
        this.level = level;
        this.email = email;
        this.phoneNo = phoneNo;
        this.createdOn = ZonedDateTime.now();
        this.updatedOn = ZonedDateTime.now();
    }

}
