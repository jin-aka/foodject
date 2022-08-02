package com.foodject.userorder;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.foodject.biz.UserOrdersBiz;
import com.foodject.vo.UserOrdersVO;

@SpringBootTest
class InsertTest {

	@Autowired
	UserOrdersBiz biz;

	@Test
	void contextLoads() {
		UserOrdersVO vo = new UserOrdersVO(0, "id02", 1, "서울시", "동동동", "010-2222-4444", "nick", null, "dddd");
//		vo = biz.register(vo);
		try {
			biz.register(vo);
			System.out.println(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
