package com.foodject.usercart;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.foodject.biz.UserCartBiz;
import com.foodject.vo.UserCartVO;

@SpringBootTest
class UserCartInsertTest {

	@Autowired
	UserCartBiz biz;

	@Test
	void contextLoads() {
		UserCartVO obj = new UserCartVO("id01",5);
		int num = 0;
		try { 
			obj = biz.getForInsert(obj);
			num = obj.getNum();
			
			UserCartVO nobj = new UserCartVO(1,num+1);
			biz.modify(nobj);
			
			
			System.out.println(biz.get(1));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
