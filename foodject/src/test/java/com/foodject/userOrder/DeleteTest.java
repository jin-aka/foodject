package com.foodject.userorder;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.foodject.biz.UserOrdersBiz;
import com.foodject.vo.UserOrdersVO;

@SpringBootTest
class DeleteTest {

	@Autowired
	UserOrdersBiz biz;

	@Test
	void contextLoads() {
		UserOrdersVO vo = new UserOrdersVO(2, 2);
		try {
			biz.modifysts(vo);;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
