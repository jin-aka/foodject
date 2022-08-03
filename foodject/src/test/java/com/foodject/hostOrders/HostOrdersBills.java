package com.foodject.hostOrders;

import java.util.List;

import com.foodject.biz.HostOrdersBiz;
import com.foodject.vo.HostOrdersVO;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HostOrdersBills {

	@Autowired
	HostOrdersBiz biz;

	@Test
	void contextLoads() {
		System.out.println("dd");
		List<HostOrdersVO> list = null;
		try {
			list = biz.selectbills(1);

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("list : "+list);
	}

}
