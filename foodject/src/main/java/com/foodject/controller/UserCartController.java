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
import com.foodject.vo.UserShopVO;

@Controller
@RequestMapping("/cart")
public class UserCartController {
	
	@Autowired
	UserCartBiz crbiz;
	
	@Autowired
	UserOptcartBiz ocbiz;
	
	@Autowired
	UserShopBiz sbiz;

	@RequestMapping("")
	public String main(Model m, String uid, HttpSession session, String prevUrl) {
		UserCustVO cust = (UserCustVO) session.getAttribute("loginid");
		if(cust == null) {
			return "redirect:/cust/login?prevUrl="+prevUrl;
		}else if(cust.getId().equals(uid) == false) {
			return "redirect:/custUidError";
		}else{	
			int sid = 0;
			String id = cust.getId();
			String nickname = cust.getNick();
			UserShopVO shop = null;
			List<UserCartVO> crlist = null;
			List<UserOptcartVO> oclistForSize = null;
			List<UserOptcartVO> oclist = null;
			UserCartVO cartForCount = null;
				
			int ctid = 0;
			int cnt = 0;
				
			try {
				sid = crbiz.getSid_byUid(uid);
				
				shop = sbiz.get(sid);
				m.addAttribute("shop",shop);
				
				UserCartVO obj = new UserCartVO(0, uid,sid);
				crlist = crbiz.get_byUid(obj);
				m.addAttribute("crlist",crlist);
					
				oclist = ocbiz.get_byUid(uid);
					
				for (UserCartVO cr : crlist) {
					ctid = cr.getId();
					System.out.println(ctid);
					oclistForSize = ocbiz.get_byCtid(cr.getId());
					for (UserOptcartVO oc : oclistForSize) {
						System.out.println(oc);
					}
					cnt = ocbiz.getCount(ctid);
					cr.setCount(cnt);
				}	
				m.addAttribute("oclist", oclist);
				
			} catch (Exception e) {
				e.printStackTrace();
			}	
				
			m.addAttribute("user",id+"( "+nickname+" ) ");
			m.addAttribute("center", "user/cart/center" );
		}		
		return "user/index";
	}			
				
	@RequestMapping("/quantity_modify")
	public String quantity_modify(Model m, HttpSession session) {
		UserCustVO cust = (UserCustVO) session.getAttribute("loginid");
		String uid = cust.getId();
				
		return "redirect:/cart";
	}
}