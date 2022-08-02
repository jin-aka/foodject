package com.foodject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.foodject.biz.UserCollectionBiz;
import com.foodject.biz.UserMenuBiz;
import com.foodject.biz.UserOptBiz;
import com.foodject.biz.UserShopBiz;
import com.foodject.vo.MarkerVO;
import com.foodject.vo.UserCollectionVO;
import com.foodject.vo.UserMenuVO;
import com.foodject.vo.UserOptVO;
import com.foodject.vo.UserShopVO;

@Controller
@RequestMapping("/shop")
public class UserShopController {
	
	@Autowired
	UserShopBiz sbiz;  
	
	@Autowired
	UserMenuBiz mnbiz;
	
	@Autowired
	UserCollectionBiz cbiz;
	
	@Autowired
	UserOptBiz obiz;
	
	
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
	public String shop(Model m, int cid, double latt, double logt) {
		MarkerVO obj = new MarkerVO(latt,logt,cid);
		List<UserShopVO> list = null;
		
		try {	
			list = sbiz.getMain(obj);
			m.addAttribute("shoplist",list);
			m.addAttribute("center", "user/shop/center");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "user/index";
	}
	
	@RequestMapping("/main")
	public String main(Model m, int sid) {
		List<UserMenuVO> mlist = null;
		List<UserCollectionVO> clist = null;
		List<UserOptVO> olist = null;
		UserShopVO obj = null;
		
		try {
			m.addAttribute("sid",sid);
			
			obj = sbiz.get(sid);
			m.addAttribute("shop",obj);
			
			clist = cbiz.get_byShop(sid);
			m.addAttribute("clist",clist);
			
			mlist = mnbiz.get_byShop(sid);
			m.addAttribute("mlist",mlist);
			
			olist = obiz.get_byShop(sid);
			m.addAttribute("olist",olist);
			
			m.addAttribute("center","/user/shop/main");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		m.addAttribute(mlist);
		return "user/index";
	}
	
	@RequestMapping("/addOptcart")
	public String addOptcart(Model m, int sid, int cartId ,int[] option) {
		if(cartId == 0) {
			System.out.println("cartId is null");
			return "redirect:/shop/main?sid="+sid;
		}
		
		System.out.println("cartId is "+cartId);
		
		if(option == null) {
			
			System.out.println("Option is null");
		
		}else {
			System.out.println("Enter java");
			for (int i : option) {
				System.out.println(cartId+" to add "+i);
			}
			
		}
		
		return "redirect:/shop/main?sid="+sid;
	}
	

}
