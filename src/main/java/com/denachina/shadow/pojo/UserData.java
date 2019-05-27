package com.denachina.shadow.pojo;

import lombok.Data;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Date;

@Data
public class UserData {

    private Integer userId;

    private String userName;

    private String sexType;

    private Date birthday;

    private String jobName;

    private Short interest;

    private String intro;

    private Short isFreeze;

    private Short userStatus;

    private Integer level;

    private String email;

    private String phoneNo;

    private Date createdOn;

    private Date updatedOn;

    public UserData(){
        this.createdOn = new Date();
        this.updatedOn = new Date();
    }

    public UserData(String userName,String sexType, Date birthday,String jobName,String intro,Integer level,String email,String phoneNo){
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
        this.createdOn = new Date();
        this.updatedOn = new Date();
    }

}
