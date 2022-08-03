package com.foodject.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.foodject.vo.UserCustVO;

@Controller
@RequestMapping("/cart")
public class UserCartController {
	
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
	public String main(Model m, String uid, HttpSession session, String prevUrl) {
		UserCustVO cust = (UserCustVO) session.getAttribute("loginid");
		boolean error = true;
		if(cust == null) {
			return "redirect:/cust/login?prevUrl="+prevUrl;
		}else if(cust.getId().equals(uid) == false) {
			error = false;
		}else{
			m.addAttribute("center", "user/cart/center" );
		}
		m.addAttribute("uid_error",error);
		return "user/index";
	}
	
	

}
