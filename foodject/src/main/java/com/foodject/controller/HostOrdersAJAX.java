package com.foodject.controller;


import java.util.List;

import javax.servlet.http.HttpSession;

import com.foodject.biz.HostOrdersBiz;
import com.foodject.biz.HostShopBiz;
import com.foodject.vo.HostManagerVO;
import com.foodject.vo.HostOrdersVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/host/orders")
public class HostOrdersAJAX {

	@Autowired
	HostShopBiz sbiz;
	@Autowired
	HostOrdersBiz biz;
	

	@RequestMapping("getDatas")
	public Object getDatas(ModelAndView mv, HttpSession session, int id) {
		HostManagerVO manager = null;
		HostOrdersVO obj = null;
		List<HostOrdersVO> list = null;

		if (session.getAttribute("loginshop") == null) {
			mv.setViewName("redirect:/host");
			return mv;
		}
		manager = (HostManagerVO) session.getAttribute("loginshop");
		System.out.println("getDatas get id : " +manager.getId());
		System.out.println("int id  : " + id);
		obj = new HostOrdersVO(id, manager.getId());
		


		System.out.println("getDatas ojb : " + obj);
		try {
			list = biz.selectorders(obj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("list : " + list);
	
		return list;
	}
	

}

