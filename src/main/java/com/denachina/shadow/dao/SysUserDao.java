package com.denachina.shadow.dao;

import com.denachina.shadow.pojo.SysUser;
import org.springframework.stereotype.Component;

@Component
public interface SysUserDao {
    /**
     * 根据用户名和密码查询对应的用户
     *
     * @param username 用户名
     * @param password 密码
     * @return
     */
    SysUser getSysUserInfo(String username, String password);
}
