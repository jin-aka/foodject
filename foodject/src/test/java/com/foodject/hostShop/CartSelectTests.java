package com.foodject.hostShop;

import java.util.List;

import com.foodject.biz.HostCollectionBiz;
import com.foodject.vo.HostCollectionVO;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CartSelectTests {

	@Autowired
	HostCollectionBiz biz;
	
	@Test
	void contextLoads() {
		HostCollectionVO co = new HostCollectionVO("음료", 8, 1);
		try {
				biz.register(co);
				System.out.println("register OK");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}


