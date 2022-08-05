package com.foodject.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserMainController {
	
	@Value("${kakaoJSKey}")
	String kakaoJSKey;
	
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
	@RequestMapping("/sample")
	public String sample(Model m) {
		return "user/sample";
	}
	
	@RequestMapping("/")
	public String main(Model m) {
		m.addAttribute("kakaosrc",kakaoJSKey);
		return "user/index";
	}
	

	

}
