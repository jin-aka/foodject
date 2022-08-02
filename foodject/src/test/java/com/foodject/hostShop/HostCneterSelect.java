package com.foodject.hostShop;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.foodject.biz.HostShopBiz;
import com.foodject.vo.HostShopVO;

@SpringBootTest
class HostCneterSelect {

	@Autowired
	HostShopBiz biz;

	@Test
	void contextLoads() {
		System.out.println("dd");
		List<HostShopVO> vo = null;
		try {
			vo = biz.getmid("mid01");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(vo);
	}

}
