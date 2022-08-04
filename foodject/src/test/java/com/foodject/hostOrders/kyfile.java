package com.foodject.hostOrders;

import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.foodject.biz.HostShopBiz;
import com.foodject.vo.HostShopVO;

@SpringBootTest
class kyfile {

	@Autowired
	HostShopBiz biz;

	@Test
	void contextLoads() {
		
		System.out.println(Paths.get(System.getProperty("user.dir"), "foodject", "src", "main","resources","static","foodject" ).toString() );
		
	}

}
