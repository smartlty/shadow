package com.denachina.shadow;

import com.denachina.shadow.pojo.SysUser;
import com.denachina.shadow.pojo.UserData;
import com.denachina.shadow.service.SysUserService;
import com.denachina.shadow.service.UserDataService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShadowApplicationTests {

	@Autowired
	SysUserService sysUserService;

	@Autowired
	UserDataService userDataService;

	@Test
	public void contextLoads() {
		SysUser sysUser = sysUserService.getSysUserInfo("admin","123456");
		System.out.println(sysUser);

		List<UserData> userDataList = userDataService.getAllUserData();
		System.out.println(userDataList);
	}

}

