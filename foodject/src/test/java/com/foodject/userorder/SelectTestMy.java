package com.foodject.userorder;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.foodject.biz.UserOrdersBiz;
import com.foodject.vo.UserOrdersMyVO;

@SpringBootTest
class SelectTestMy {

	@Autowired
	UserOrdersBiz biz;

	@Test
	void contextLoads() {
		List<UserOrdersMyVO> vo = null;
		try {
			vo = biz.getmy("id01");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(vo);
	}

}
