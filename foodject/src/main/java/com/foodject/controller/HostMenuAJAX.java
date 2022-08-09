package com.foodject.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodject.biz.HostMenuBiz;
import com.foodject.biz.UserOptBiz;
import com.foodject.vo.UserOptVO;

@RestController
@RequestMapping("/host/menu")
public class HostMenuAJAX {

	@Autowired
	HostMenuBiz mbiz;
	@Autowired
	UserOptBiz obiz;
	
	@RequestMapping("optupt")
	public Object optupt(Model m,  int id, int price, String name) {
		
		System.out.println(id+name+price);
		
		UserOptVO opt = new UserOptVO(id, name, price);
		try {
			obiz.modify(opt);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return opt;
	}
	
	

}

