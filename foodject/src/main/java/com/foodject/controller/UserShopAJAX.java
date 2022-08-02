package com.foodject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodject.biz.UserCartBiz;
import com.foodject.biz.UserOptcartBiz;
import com.foodject.vo.UserCartVO;
import com.foodject.vo.UserOptcartVO;

@RestController
@RequestMapping("/shop/main")
public class UserShopAJAX {
	
	@Autowired
	UserCartBiz crbiz;
	
	@Autowired
	UserOptcartBiz opbiz;
	
	@RequestMapping("/addCart")
	public Object addCart(String uid, int mnid, int num, int chk) {
		int cartId = 0;
		int cnt = 0;
		
		List<UserCartVO> list = null;
		UserCartVO obj = null;
		UserCartVO OFI = new UserCartVO(uid, mnid);
		List<UserOptcartVO> oclist = null;
		
		if (chk == 0) {
		// chk=0 : 옵션이 없음.
			try {
				//selectForInsert 해보고
				list = crbiz.getForInsert(OFI);
				
				// for문으로 일치하는 항목 중 각각 optcart을 가지고있는지 확인
				for (UserCartVO cart : list) {
					cartId = cart.getId();
					oclist = opbiz.get_byCtid(cartId);
					
					//optcart 없는 항목찾으면 .getId하고 cnt에 1 넣고 break
					if(oclist.isEmpty()) {
						cnt = 1;
						obj = cart;
						break;
					}else {

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
		}else {
		// chk=1 : 옵션이 존재 
			// 옵션이 있으면 다른 상품으로 생각하고 새로 cartId에 넣는다.
			System.out.println("case2");
			obj = new UserCartVO(uid,mnid,num);
			try {
				crbiz.register(obj);
				cartId = obj.getId();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return cartId;
	}
}