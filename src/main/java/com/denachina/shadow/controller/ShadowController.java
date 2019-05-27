package com.denachina.shadow.controller;

import com.denachina.shadow.pojo.UserData;

import com.denachina.shadow.service.SysUserService;
import com.denachina.shadow.service.UserDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping(value = "/userdata")
public class ShadowController {

    private final static Logger logger = LoggerFactory.getLogger(ShadowController.class);

    @Autowired
    UserDataService userDataService;

    @Autowired
    SysUserService sysUserService;

    @RequestMapping(value = "/list")
    public List<UserData> findAll(HttpServletRequest request){
        String data = request.getQueryString();
        logger.info(data);

        Map<String, String[]> requestM = request.getParameterMap();

        Map<String, String> resultM = new HashMap<String, String>();

        for (String k : requestM.keySet()) {
            String v = requestM.get(k)[0];
            resultM.put(k, v);
        }
        logger.info(resultM.toString());
        Map<String, String> map = new HashMap<>();
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        logger.info(map.toString());
        List<UserData> userDataList = userDataService.getAllUserData();

//        produceMessage.send("执行查询操作,UTC时间 "+Instant.now());
        return userDataList;
    }

    @RequestMapping(value = "/update")
    public int updateJob(HttpServletRequest request, @RequestParam("userId") Integer userId, @RequestParam("jobName") String jobName){

//        produceMessage.send("执行查询更新,UTC时间 "+Instant.now() + "; 更新的 userId "+ userId);

        return userDataService.updateUserDataJobname(userId, jobName);
    }

    @RequestMapping(value = "/add")
    public int insertUser(HttpServletRequest request){
        String userName = "劳拉";
        String sexType = "female";
        String jobName = "tomb raider";
        String intro = "Life lies in adventure!";
        Integer level = 100;
        String email = "laola@gmail.com";
        String phoneNo = "+90666666";
        UserData userData = new UserData(userName,sexType,null,jobName,intro,level,email,phoneNo);
        logger.info("insert into table , data {}", userData);

        int ret= userDataService.insertUserData(userData);

//        produceMessage.send("执行新增操作,UTC时间 "+Instant.now() + " ; 插入对象 "+ userData1);

        return ret;
    }
    @RequestMapping(value = "/delete")
    public int deleteUser(HttpServletRequest request, @RequestParam("userId") Integer userId){
        logger.info("delete record by userId {}", userId);

//        produceMessage.send("执行查询更新,UTC时间 "+Instant.now() + "; 更新的 userId "+ userId);

        return userDataService.deleteUserData(userId);
    }

    @PostMapping(value = "/update")
    @Transactional
    public boolean update(HttpServletRequest request, @RequestParam Integer userId, @RequestParam String username) throws Exception {
        logger.info("update record by userId {}", userId);

//        produceMessage.send("执行查询更新,UTC时间 "+Instant.now() + "; 更新的 userId "+ userId);
        boolean ret = sysUserService.updateById(userId, username);

        return ret;
    }
}
