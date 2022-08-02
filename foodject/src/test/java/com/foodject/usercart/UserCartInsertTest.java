package com.foodject.usercart;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.foodject.biz.UserCartBiz;
import com.foodject.vo.UserCartVO;

@SpringBootTest
class UserCartInsertTest {

	@Autowired
	UserCartBiz biz;

	@Test
	void contextLoads() {
		UserCartVO obj = new UserCartVO("id02",1,2);
		int num = 0;
		try { 
			biz.register(obj);
			num = obj.getId();
			System.out.println(num);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
