package com.foodject.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.foodject.biz.UserCartBiz;
import com.foodject.biz.UserOptcartBiz;
import com.foodject.biz.UserOrdersBiz;
import com.foodject.biz.UserShopBiz;
import com.foodject.vo.UserCartVO;
import com.foodject.vo.UserCustVO;
import com.foodject.vo.UserOptcartVO;
import com.foodject.vo.UserOrdersVO;
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

	@Autowired
	UserOrdersBiz obiz;
	
	@RequestMapping("paytest")
	public String paytest(Model m) {
		m.addAttribute("center", "user/cart/center2" );
		return "user/index";
	}		

	
	@RequestMapping("")
	public String main(Model m, String uid, HttpSession session, String prevUrl) {
		UserCustVO cust = (UserCustVO) session.getAttribute("loginid");

		if(cust == null) {
			return "redirect:/cust/login?prevUrl="+prevUrl;
		}else if(cust.getId().equals(uid) == false) {
			return "redirect:/custUidError";
		}else{	
			int count = 0;
			int sid = 0;
			String id = cust.getId();
			String nickname = cust.getNick();
			UserShopVO shop = null;
			List<UserCartVO> crlist = null;
			List<UserOptcartVO> oclist = null;
				
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
//					System.out.println(ctid);
					//oclistForSize = ocbiz.get_byCtid(cr.getId());
//					for (UserOptcartVO oc : oclistForSize) {
//						System.out.println(oc);
//					}
					cnt = ocbiz.getCount(ctid);
					cr.setCount(cnt);
				}	
				m.addAttribute("oclist", oclist);

				
			} catch (Exception e) {
				m.addAttribute("count",count);
				System.out.println("cart is empty");
			}	
				
			m.addAttribute("user",id+"( "+nickname+" ) ");
			m.addAttribute("center", "user/cart/center" );


		}		
		return "user/index";
	}			
	
	@RequestMapping("/delete")
	public String deleteCart(Model m, String uid, int cartId, HttpSession session, String prevUrl) {
		UserCustVO cust = (UserCustVO) session.getAttribute("loginid");
		if(cust == null) {
			return "redirect:/cust/login";
		}else if(cust.getId().equals(uid) == false) {
			
			return "redirect:/pathError";
		}else{
			//System.out.println("delete");
			try {
				crbiz.remove(cartId);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "redirect:"+prevUrl;
		}
	}
	
	@RequestMapping("order")
	public String orderimple(Model m, String uid, HttpSession session) {
		UserCustVO cust = (UserCustVO) session.getAttribute("loginid");
		
		if(cust == null) {
			return "redirect:/cust/login";
		}else if(cust.getId().equals(uid) == false) {
			return "redirect:/pathError";
		}else{
			int sid=0;
			List<UserCartVO> crlist = null;
			List<UserOptcartVO> oclist = null;
			UserOrdersVO order = null;
			
			
			/* 매개변수 */
			String addr = "주소";
			String addrd = "상세주소";
			String phon = "010-0000-00000";
			String nick = cust.getNick();
			String ask = "많이 주세요.";
					
			
			try {
				sid = crbiz.getSid_byUid(uid);
				UserCartVO obj = new UserCartVO(0, uid,sid);
				crlist = crbiz.get_byUid(obj);
				oclist = ocbiz.get_byUid(uid);
				
				order = new UserOrdersVO(uid,sid,addr,addrd,phon,nick,ask);
				obiz.register(order);
				
				
				
				for (UserCartVO cart : crlist) {
					System.out.println(cart);
					
				}
				
				
				
				
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			return "redirect:/";
		}
	}
				
//	@RequestMapping("/quantity_modify")
//	public String quantity_modify(Model m, HttpSession session) {
//		UserCustVO cust = (UserCustVO) session.getAttribute("loginid");
//		String uid = cust.getId();
//				
//		return "redirect:/cart";
//	}
}