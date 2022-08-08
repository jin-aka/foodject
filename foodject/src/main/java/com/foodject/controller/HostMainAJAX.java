package com.foodject.controller;


import javax.servlet.http.HttpSession;

import com.foodject.biz.HostManagerBiz;
import com.foodject.biz.HostMenuBiz;
import com.foodject.vo.HostManagerVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/host")
public class HostMainAJAX {

	@Autowired
	HostManagerBiz mngbiz;
	
	@Autowired
	HostMenuBiz mnbiz;
	
	
	
	@RequestMapping("mainNum")
	public String mainNum(Model m, HttpSession session,  int cartid, int num) {
		String result = "";
//		CartVO cv = null;
//		CartVO cart = new CartVO(cartid, num);
//		try {
//			cartbiz.modify(cart);
//			cv = cartbiz.get(cartid);
//			result = Integer.toString(cv.getPrice());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

		return result;
	}
//	@RequestMapping("/registerimpl")
//	public String registerimpl(Model m, HostManagerVO manager, HttpSession session) {
//		
//		try {
//			mngbiz.register(manager);
//			session.setAttribute("loginshop", manager);
//		} catch (Exception e) {
//			
//			e.printStackTrace();
//		}
//		return "host/index";
//	}
	
	@RequestMapping("checkid")
	public String checkid(String id) {
		String result = "";
		HostManagerVO manager = null;
		
		if(id.equals("") || id == null) {
			return "1";
		}
		
		try {
			manager = mngbiz.get(id);
			if(manager == null  && id.length()>3 ) {
				result = "0";
			}else {
				result = "1";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
//	@RequestMapping("mnprice")
//	public String mnprice(Model m,  int id, int price) {
//		String result = "";
//		HostMenuVO mn = null;
//		HostMenuVO menu = new HostMenuVO(id, price);
//		try {
//			mnbiz.modify(menu);
//			mn = mnbiz.get(id);
//			result = Integer.toString(mn.getPrice());
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		return result;
//	}

}

