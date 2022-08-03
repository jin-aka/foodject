package com.foodject.hostShop;

import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.foodject.biz.HostCollectionBiz;

@SpringBootTest
class CartSelectTests {

	@Autowired
	HostCollectionBiz biz;
	
	@Test
	void contextLoads() {
		System.out.println(Paths.get(System.getProperty("user.dir"), "foodject", "src", "main","resources","static","foodject" ).toString() );
	}
}


