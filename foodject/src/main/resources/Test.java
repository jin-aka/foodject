package com.foodject.controller;


import java.util.List;

import javax.servlet.http.HttpSession;

import com.foodject.biz.UserCustBiz;
import com.foodject.biz.UserOrdersBiz;
import com.foodject.frame.Util;
import com.foodject.restapi.BcrytPassward;
import com.foodject.vo.UserCustVO;
import com.foodject.vo.UserOrdersMyVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;



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
	public String login(Model m, String msg) {
		if(msg != null && msg.equals("f")) {
			m.addAttribute("msg", "!! 아이디 또는 비밀번호를 확인해주세요");
		}
		m.addAttribute("center","user/cust/login");

		
		return "user/index";
	}
	
	@RequestMapping("/loginimpl")
	public String loginimpl(Model m, String id, String pwd, HttpSession session) {
		UserCustVO cust = null;
		try {
			cust = custbiz.get(id);
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
			return "redirect:login?msg=f";
		}
		return "redirect:/";
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

	@RequestMapping("/myorders")
	public String myorders(HttpSession session, Model m) {
		UserCustVO cust = (UserCustVO) session.getAttribute("loginid");
		List<UserOrdersMyVO> olist = null;
		List<UserOrdersMyVO> menu = null;
		try {
			olist = ordersbiz.getmy(cust.getId());
			m.addAttribute("olist",olist);
			m.addAttribute("menu",menu);
		} catch (Exception e) {
			e.printStackTrace();
		}
		m.addAttribute("center","/user/cust/myorders");
		return "user/index";
	}
	
	
	@RequestMapping("/myordersde")
	public String myordersde(HttpSession session, int oid, Model m) {
		UserCustVO cust = (UserCustVO) session.getAttribute("loginid");
		List<UserOrdersMyVO> olist = null;
		List<UserOrdersMyVO> list = null;
		try {
			olist = ordersbiz.getmy(cust.getId());
			list = ordersbiz.getmymenu(oid);
			System.out.println("getmy Test"+olist);
			System.out.println("getmymenu Test"+list);

		} catch (Exception e) {
			e.printStackTrace();
		}
		m.addAttribute("o",olist);
		m.addAttribute("m",list);
		
		m.addAttribute("center", "/user/cust/myordersde");
		return "user/index";
	}
	
	@RequestMapping("/cs")
	public String cs(Model m) {
		m.addAttribute("center","/user/cust/cs");
		return "user/index";
	}



}
