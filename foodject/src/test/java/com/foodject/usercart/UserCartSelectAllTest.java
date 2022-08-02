package com.foodject.usercart;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.foodject.biz.UserCartBiz;
import com.foodject.vo.UserCartVO;

@SpringBootTest
class UserCartSelectAllTest {

	@Autowired
	UserCartBiz biz;

	@Test
	void contextLoads() {
		List<UserCartVO> list = null;
		try {
			list = biz.get();
			for (UserCartVO obj : list) {
				System.out.println(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
