package com.foodject.userorder;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.foodject.biz.UserOrdersBiz;
import com.foodject.vo.UserOrdersVO;

@SpringBootTest
class UpdateTest {

	@Autowired
	UserOrdersBiz biz;

	@Test
	void contextLoads() {
		UserOrdersVO vo = new UserOrdersVO(2, "id02", 1, "부산시", "부산동", "010-2222-4444", "nick", "dddd");
		try {
			biz.modify(vo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
