package com.denachina.shadow.service;

import com.denachina.shadow.pojo.UserData;

import java.util.List;

public interface UserDataService {
    /**
     * 查询所有记录
     *
     */
    List<UserData> getAllUserData();

    /**
     * 根据工作查询对应的用户
     *
     * @param jobName 工作
     * @return
     */
    UserData getUserDataByJobName(String jobName);

    /**
     * 根据用户id更新对应的用户工作
     *
     * @param userId 用户id
     * @param jobName 工作
     * @return
     */
    int updateUserDataJobname(Integer userId, String jobName);

    /**
     * 更新用户记录
     *
     * @param userData 用户记录
     * @return
     */
    int insertUserData(UserData userData);

    /**
     * 删除记录
     *
     * @param userId 用户id
     * @return
     */
    int deleteUserData(Integer userId);
}
