package com.denachina.shadow.service.impl;

import com.denachina.shadow.dao.UserDataDao;
import com.denachina.shadow.pojo.UserData;
import com.denachina.shadow.service.UserDataService;
import com.denachina.shadow.util.DbUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDataServiceImpl implements UserDataService{

    @Autowired
    UserDataDao userDataDao;

    @Override
    public List<UserData> getAllUserData() {
        DbUtil.setPostgresDbR();
        return userDataDao.getAllUserData();
    }

    @Override
    public UserData getUserDataByJobName(String jobName) {
        DbUtil.setPostgresDbR();
        return userDataDao.getUserDataByJobName(jobName);
    }

    @Override
    public int updateUserDataJobname(Integer userId, String jobName) {
        DbUtil.setPostgresDbW();
        int ret = 0;
        try {
            ret = userDataDao.updateUserDataJobname(userId, jobName);
        } catch (Exception e){
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public int insertUserData(UserData userData) {
        DbUtil.setPostgresDbW();
        int ret = 0;
        try {
            ret = userDataDao.insertUserData(userData);
        } catch (Exception e){
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public int deleteUserData(Integer userId) {
        DbUtil.setPostgresDbW();
        int ret = 0;
        try {
            ret = userDataDao.deleteUserData(userId);
        } catch (Exception e){
            e.printStackTrace();
        }
        return ret;
    }

}
