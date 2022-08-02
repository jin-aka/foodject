package com.foodject.controller;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodject.biz.UserCustBiz;
import com.foodject.biz.UserOrdersBiz;
import com.foodject.vo.UserCustVO;
import com.foodject.vo.UserOrdersMyVO;

@RestController
public class UserCustAJAX {

	@Autowired
	UserCustBiz custbiz;
	
	@Autowired
	UserOrdersBiz ordersbiz;
	
	
	@RequestMapping("/checkid")
	public String custNum(String id) {
		String result = "";
		UserCustVO cust = null;
		if(id.equals("")|| id==null) {
			return "1";
		}
		
		try {
			cust = custbiz.get(id);
			if(cust==null && id.length()>3) {
				result="0";
			}else {
				result="1";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		

		return result;
	}


}

