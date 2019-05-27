package com.denachina.shadow;

import com.denachina.shadow.pojo.SysUser;
import com.denachina.shadow.service.SysUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShadowApplicationTests {

	@Autowired
	SysUserService sysUserService;

	@Test
	@Transactional
	public void contextLoads() throws Exception {
		SysUser sysUser = sysUserService.getSysUserInfo("admin","123456");
		System.out.println(sysUser);

/*		boolean ret = false;
		try {
			ret = sysUserService.updateById(1, "admin");
		} catch (Exception e) {
			e.printStackTrace();
		}*/

		boolean ret = sysUserService.updateById(1, "bookman");

		System.out.println("ret = " + ret);
	}

}

