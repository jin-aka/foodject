package com.foodject.userorder;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.foodject.biz.UserOrdersBiz;
import com.foodject.vo.UserOrdersVO;

@SpringBootTest
class SelectTest {

	@Autowired
	UserOrdersBiz biz;

	@Test
	void contextLoads() {
		UserOrdersVO vo = null;
		try {
			vo = biz.get(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(vo);
	}

}
