package com.denachina.shadow;

import com.denachina.shadow.dao.SysUser;
import com.denachina.shadow.dao.SysUserRepository;
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
	SysUserRepository sysUserRepository;

	@Test
	public void contextLoads() {
		List<SysUser> sysUsers = sysUserRepository.findAll();
		System.out.println(sysUsers);
	}

}

