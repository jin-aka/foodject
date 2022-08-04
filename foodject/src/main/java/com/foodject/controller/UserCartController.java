package com.foodject.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.foodject.biz.UserCartBiz;
import com.foodject.biz.UserOptcartBiz;
import com.foodject.biz.UserShopBiz;
import com.foodject.vo.UserCartVO;
import com.foodject.vo.UserCustVO;
import com.foodject.vo.UserOptcartVO;

@Controller
@RequestMapping("/cart")
public class UserCartController {
	
	@Autowired
	UserCartBiz crbiz;
	
	@Autowired
	UserOptcartBiz ocbiz;
	
	@Autowired
	UserShopBiz sbiz;
	
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
		if(cust == null) {
			return "redirect:/cust/login?prevUrl="+prevUrl;
		}else if(cust.getId().equals(uid) == false) {
			return "redirect:/errorpage";
		}else{
			String id = cust.getId();
			String nickname = cust.getNick();
			List<UserCartVO> crlist = null;
			List<UserOptcartVO> oclist = null;
			
			try {
				crlist = crbiz.get();
				m.addAttribute("crlist",crlist);
				
				oclist = ocbiz.get();
				m.addAttribute("oclist",oclist);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			m.addAttribute("user",id+"( "+nickname+" ) ");
			m.addAttribute("center", "user/cart/center" );
		}
		return "user/index";
	}
	
	

}
