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
		int chk=0;
		
		int cartId = 0;
		int cnt = 0;
		
		List<UserCartVO> list = null;
		UserCartVO obj = null;
		UserCartVO OFI = new UserCartVO(uid, mnid);
		List<UserOptcartVO> oclist = null;
		
		switch (chk) {
		// chk=0 : 옵션이 없음.
		case 0 : 
			try {
				//selectForInsert 해보고
				list = crbiz.getForInsert(OFI);
				
				// for문으로 일치하는 항목 중 각각 optcart을 가지고있는지 확인
				for (UserCartVO cart : list) {
					cartId = cart.getId();
					System.out.println(cart);
					oclist = opbiz.get_byCtid(cartId);
					
					//optcart 없는 항목찾으면 .getId하고 cnt에 1 넣고 break
					if(oclist.isEmpty()) {
						cnt = 1;
						obj = cart;
						break;
					}else {
						for (UserOptcartVO oc : oclist) {
							System.out.println(oc);
						}
						
					}
				}
				// if(cnt가 1이면) 없는 항목에 num랑 더한 값으로 updateNum 해주기.
				if(cnt == 1) {
					System.out.println("case1 modify");
					crbiz.modify(new UserCartVO(cartId,obj.getNum()+num));
				}else {
					// else(cnt가 0) 옵션없는 항목이 없으면 그냥 새로 추가하고 cartId 가져오기
					System.out.println("case1 new");
					obj = new UserCartVO(uid,mnid,num);
					crbiz.register(obj);
					cartId = obj.getId();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		// chk=1 : 옵션이 존재
		case 1 : 
			// 옵션이 있으면 다른 상품으로 생각하고 새로 cartId에 넣는다.
			System.out.println("case1 new");
			obj = new UserCartVO(uid,mnid,num);
			try {
				crbiz.register(obj);
				cartId = obj.getId();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		System.out.println("cnt:"+cnt);
		System.out.println("cartId:"+cartId);
	}
}
