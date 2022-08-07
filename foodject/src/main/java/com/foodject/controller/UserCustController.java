package com.foodject.controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.foodject.biz.UserCustBiz;
import com.foodject.biz.UserOrdersBiz;
import com.foodject.frame.Util;
import com.foodject.restapi.BcrytPassward;
import com.foodject.vo.UserCustVO;
import com.foodject.vo.UserOrdersMyVO;



@Controller
@RequestMapping("/cust")
public class UserCustController {
	
	@Autowired
	UserCustBiz custbiz;
	
	@Autowired
	UserOrdersBiz ordersbiz;

	@Autowired
	Util ut;

	@Autowired
	BcrytPassward bp;
	
	@RequestMapping("/update")
	public String update(Model m, HttpSession session) {
		UserCustVO cust = (UserCustVO) session.getAttribute("loginid");
		try {
			cust = custbiz.get(cust.getId());
			m.addAttribute("c", cust);
		} catch (Exception e) {
			e.printStackTrace();
		}
		m.addAttribute("center", "/user/cust/update");
		return "user/index";
	}
	

	@RequestMapping("")
	public ModelAndView cust(ModelAndView mv, HttpSession session) {
		UserCustVO cust = (UserCustVO) session.getAttribute("loginid");
		mv.setViewName("user/index");
		try {
			cust = custbiz.get(cust.getId());
			mv.addObject("c", cust);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mv.addObject("center", "/user/cust/mypage" );
		return mv;
	}
	
	
	@RequestMapping("/login")
	public String login(Model m, String msg, String prevUrl) {
		if(msg != null && msg.equals("f")) {
			m.addAttribute("msg", "!! 아이디 또는 비밀번호를 확인해주세요");
		}
		m.addAttribute("center","user/cust/login");
		if(prevUrl != null) {
			m.addAttribute("prevUrl",prevUrl);
		}
		
		return "user/index";
	}
	
	@RequestMapping("/loginimpl")
	public String loginimpl(Model m, String id, String pwd, HttpSession session, String prevUrl) {
		UserCustVO cust = null;
		try {
			cust = custbiz.get(id);
			//System.out.println(cust);
			if(cust == null) {
				throw new Exception();

			}
			// 비밀번호 암호화 체크 처리
			// if(bp.checkPassward(cust.getPwd(), pwd)) {
			// 	session.setAttribute("loginid", cust);
			// }else {
			// 	throw new Exception();
			// }

			if(cust.getPwd().equals(pwd)) {
				session.setAttribute("loginid", cust);
			}else {
				throw new Exception();
			}
		} catch (Exception e) {
			return "redirect:/cust/login?msg=f&prevUrl="+prevUrl;
		}
		if(prevUrl != null && !(prevUrl.equals("null")) && !(prevUrl.equals(""))) {
			return "redirect:"+prevUrl;
		}else {
			return "redirect:/";
		}
		
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		if(session != null) {
			session.invalidate();
		}
		return "redirect:/";
	}

	@RequestMapping("/register")
	public String register(Model m) {
		return "user/cust/register";
	}
	
	@RequestMapping("/registerimpl")
	public String registerimpl(Model m, UserCustVO cust, String img) {
		//이미지파일 이름 저장
		String imgname = cust.getMf().getOriginalFilename();
		String[] splitname = imgname.split("[.]");
		String idname = cust.getId();
    	String savename = idname + "." + splitname[splitname.length -1];

		// 비밀번호 암호화 처리
		//cust.setPwd(bp.hashPassward(cust.getPwd()));


		if(savename.equals(idname+".")) {
			cust.setImg("icon.jpg");			
		}else {
			cust.setImg(savename);
			try {
				custbiz.register(cust);
				// saveFile(실제 파일, 저장할 이름, 사용되는 DB 컬럼명)
				ut.saveFile(cust.getMf(), savename, "cust");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}				
		return "redirect:/";

	}

	@RequestMapping("/updateimpl")
	public String updateimpl(Model m, UserCustVO cust, String img) {
		String imgname = cust.getMf().getOriginalFilename();
		String[] splitname = imgname.split("[.]");
		String idname = cust.getId();
		String savename = idname + "." + splitname[splitname.length -1];		
		
		if(savename.equals(idname+".")) {
			cust.setImg("icon.jpg");			
		}else {
			cust.setImg(savename);
			try {
				Util ut = new Util();
				custbiz.modify(cust);
				ut.saveFile(cust.getMf(), savename, "cust");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}		
		
		return "redirect:/cust/update";
	}
	
	@RequestMapping("/delete")
	public String updatests(UserCustVO cust, HttpSession session, Model m) {
		try {
			custbiz.modifysts(cust);
			session.invalidate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/";
	}
	
	@RequestMapping("/orderdelete")
	public String orderdelete(int oid, Model m) {
//		int a = status;
//		if(a==1) {
//			a = 0;
//			System.out.println(a);

//		}
		System.out.println(oid);
		try {
			ordersbiz.modifydelete(oid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/cust/myorders";
	}
	
	
	
	@RequestMapping("/cs")
	public String cs(Model m) {
		m.addAttribute("center","/user/cust/cs");
		return "user/index";
	}
	
	

	@RequestMapping("/myorders")
	public String myorders(HttpSession session, Model m) {
		UserCustVO cust = (UserCustVO) session.getAttribute("loginid");
		List<UserOrdersMyVO> olist = null;
		try {
			olist = ordersbiz.getod(cust.getId());
			m.addAttribute("olist",olist);
		} catch (Exception e) {
			e.printStackTrace();
		}
		m.addAttribute("center","/user/cust/myorders");
		return "user/index";
	}
	
	@RequestMapping("/myordersde")
	public String myordersde(int oid, Model m) {
		UserOrdersMyVO odinfo = null;
		List<UserOrdersMyVO> details = null;
		List<Integer> getdeid = null;
		
		ArrayList<UserOrdersMyVO> getodmenu = new ArrayList<UserOrdersMyVO>();	
		UserOrdersMyVO menu = null;
		
		ArrayList<UserOrdersMyVO> getodopt = new ArrayList<UserOrdersMyVO>() ;
		List<UserOrdersMyVO> opt = null;
	
		UserOrdersMyVO allprice = null;
		
		try {			
			//odinfo 주문기본정보
			odinfo = ordersbiz.getodinfo(oid);
			m.addAttribute("i",odinfo);
			//odde 주문디테일
			details = ordersbiz.getodde(oid);
			m.addAttribute("d",details);
			
			allprice = ordersbiz.getallprice(oid);
			m.addAttribute("ap",allprice);
			
			//oddeid 주문디테일id 
			getdeid = ordersbiz.getoddeid(oid);	
			
						
			for (Integer i : getdeid) {
				// 주문메뉴리스트				
				menu = ordersbiz.getoddemenu(i);
				getodmenu.add(menu);

				opt = ordersbiz.getoddeopt(i);
				getodopt.addAll(opt);
						
				}
			
			m.addAttribute("m",getodmenu);
			m.addAttribute("opt", getodopt);	
			
		} catch (Exception e) {
		}
		m.addAttribute("center","/user/cust/myordersde");
		
		return "user/index";
	}




}