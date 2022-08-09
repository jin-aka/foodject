package com.foodject.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.foodject.biz.HostOrdersBiz;
import com.foodject.biz.HostShopBiz;
import com.foodject.vo.HostManagerVO;
import com.foodject.vo.HostShopVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/host/orders")
public class HostOrdersController {
	
	@Autowired
	HostOrdersBiz biz;
	@Autowired
	HostShopBiz sbiz;

	public void mainProduct(Model m) {
//		List<ProductVO> plist = null;
//		String pimgpath = Paths.get(System.getProperty("user.dir"), "src", "main","resources","static","img", "product_img").toString();
//		System.out.println("imgpath : " +  pimgpath);
//		try {	
//			plist = mainbiz.get();
//			m.addAttribute("plist", plist);
//			m.addAttribute("imgpath", pimgpath);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

	@RequestMapping("")
	public ModelAndView orders(ModelAndView mv, HttpSession session) {

		HostManagerVO manager = null;
		List<HostShopVO> list = null;
		if (session.getAttribute("loginshop") == null) {
			mv.setViewName("redirect:/host");
			return mv;
		}
		manager = (HostManagerVO) session.getAttribute("loginshop");
		String mid = manager.getId();
		try {
			list = sbiz.getmid(mid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (list.size() != 0) {
			mv.addObject("slist", list);
		}
		

		mv.setViewName("host/index");
		mv.addObject("center", "host/orders/center" );
		return mv;
	}

	@RequestMapping("/list")
	public ModelAndView orderslist(ModelAndView mv) {
		mv.setViewName("host/index");
		mv.addObject("center", "host/orders/delivery" );
		return mv;
	}
}
