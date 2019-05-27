package com.denachina.shadow.service.impl;

import com.denachina.shadow.dao.SysUserDao;
import com.denachina.shadow.pojo.SysUser;
import com.denachina.shadow.service.SysUserService;
import com.denachina.shadow.util.DbUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    SysUserDao sysUserDao;

    @Override
    public SysUser getSysUserInfo(String username, String password) {
        DbUtil.setDbR();
        return sysUserDao.getSysUserInfo(username, password);
    }

    @Override
    public boolean updateById(Integer userId, String username){
        DbUtil.setDbW();
        boolean ret = sysUserDao.updateById(userId, username);
        if (ret){
            throw new RuntimeException();
        }
        return ret;
    }

}
