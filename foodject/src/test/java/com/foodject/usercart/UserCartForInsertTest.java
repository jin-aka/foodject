package com.foodject.usercart;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.foodject.biz.UserCartBiz;
import com.foodject.biz.UserOptcartBiz;
import com.foodject.vo.UserCartVO;
import com.foodject.vo.UserOptcartVO;

@SpringBootTest
class UserCartForInsertTest {

	@Autowired
	UserCartBiz crbiz;

	@Autowired
	UserOptcartBiz opbiz;
	
	@Test
	void contextLoads() {
		String uid="id01";
		int mnid=5;
		int num=1;
		
		int cartId = 0;
		
		UserCartVO obj = null;
		UserCartVO OFI = new UserCartVO(uid, mnid);
		List<UserOptcartVO> oclist = null;
		try {
			// selectForInsert 해보고,
			System.out.println("fuck");
			obj = crbiz.getForInsert(OFI);
			// if) 일치하는 거 있으면, 일단 .getId() 
			System.out.println("fuck2");
			if(obj != null) {
				System.out.println("if1");
				cartId = obj.getId();
				// optcart도 cartId로 select 해서,
				oclist = opbiz.get_byCtid(cartId);
				
				// if) 해당 cartId에 optcart항목이 존재하면
				if(oclist != null) {
					System.out.println("if2");
				//새로운 cartId로 추가.
					crbiz.register(new UserCartVO(uid,mnid,num));
				}
				// else) 옵션 없으면 num랑 더한 값으로 updateNum 해주고, 
				else {
					System.out.println("else2");
					crbiz.modify(new UserCartVO(cartId,obj.getNum()+num));
				}
			}else {
				System.out.println("else1");
			// else) 일치하는 거 없으면 새로 추가한 후 .getId() 가져옴.
				crbiz.register(new UserCartVO(uid,mnid,num));
				obj = crbiz.getForInsert(OFI);
				cartId = obj.getId();
			}
			//if end

		} catch (Exception e) {
			System.out.println("exception");
			e.printStackTrace();
		}
		System.out.println(cartId);
	}
}
