package com.foodject.marker;

import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.foodject.biz.UserMarkerBiz;
import com.foodject.vo.MarkerVO;

@SpringBootTest
class MarkerSelelctAllTests {
	
	@Autowired
	UserMarkerBiz biz;
	
	@Test
	void contextLoads() {
		List<MarkerVO> list = null;
		try {
			list = biz.get();
			for (MarkerVO obj : list) {
				System.out.println(obj);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Paths.get(System.getProperty("user.dir"), "foodject", "src", "main","resources","static","foodject" ).toString() );
	}

}
