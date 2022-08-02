package com.foodject.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.foodject.biz.HostCollectionBiz;
import com.foodject.biz.HostMenuBiz;
import com.foodject.biz.HostShopBiz;
import com.foodject.frame.Util;
import com.foodject.vo.HostCollectionVO;
import com.foodject.vo.HostManagerVO;
import com.foodject.vo.HostMenuVO;
import com.foodject.vo.HostShopVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/host/menu")
public class HostMenuController {
	
	@Autowired
	HostShopBiz biz;
	@Autowired
	HostCollectionBiz cbiz;
	@Autowired
	HostMenuBiz mbiz;
	
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
	public String orders(Model m) {
		m.addAttribute("center", "host/menu/center");
		return "host/index";
	}

	@RequestMapping("/setting")
	public ModelAndView setting(ModelAndView mv, HttpSession session ) {
		HostManagerVO manager = null;
		List<HostShopVO> list = null;
		if( session.getAttribute("loginshop") == null ){
			mv.setViewName("redirect:/host");
			return mv;
		}
		manager = (HostManagerVO) session.getAttribute("loginshop");
		String mid = manager.getId();
		try {
			list = biz.getmid(mid);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if( list.size() != 0 ){
			mv.addObject("slist", list );
		}
		mv.setViewName("host/index");
		mv.addObject("center", "host/menu/setting" );
		return mv;
	}
	@RequestMapping("/col")	
	public String col(Model m, int sid, int id) {
		List<HostCollectionVO> clist = null;
		HostShopVO sh = null;
		try {
			
			clist = cbiz.getcol(sid);
			m.addAttribute("clist",clist);
			
			sh = biz.get(id);
			m.addAttribute("sh",sh);
			
			m.addAttribute("center","/host/menu/col");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "host/index";
	}
	@RequestMapping("/msel")	
	public String menu(Model m, int collid, int id) {
		List<HostMenuVO> mnlist = null;
		HostCollectionVO col = null;
		try {
			
			mnlist = mbiz.getmenu(collid);
			m.addAttribute("mnlist",mnlist);
			
			col = cbiz.get(id);
			m.addAttribute("col",col);
			
			m.addAttribute("center","/host/menu/msel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "host/index";
	}
	@RequestMapping("/msel/delete")
	public String delete(Model m, int[] sArray, int id, int collid) {

		
		try {
			for (int i = 0; i < sArray.length; i++) {
				
				mbiz.remove(sArray[i]);
			}
		} catch (Exception e) {
			e.printStackTrace();
}
		
		return "redirect:/host/menu/msel?id="+id+"&collid="+collid;
	}
	
	
	@RequestMapping("collection")
	public String collection(Model m) {
		m.addAttribute("center", "host/menu/collection");
		return "host/index";
	}
	@RequestMapping("/menuregister")
	public ModelAndView register(ModelAndView mv, int id, int collid) {
		HostCollectionVO col = null;
		List<HostMenuVO> mnlist = null;
		try {
			mnlist = mbiz.getmenu(collid);
			mv.addObject("mnlist",mnlist);
			
			col = cbiz.get(id);
			mv.addObject("col",col);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mv.setViewName("/host/index");
		mv.addObject("center", "/host/menu/register");
		return mv;
	}
//	@RequestMapping("/menuregisterimpl")
//	public String addimpl(Model m, HostMenuVO mn ) {
//		String imgname = p.getMf().getOriginalFilename();
//		mn.setImgname(imgname);
//		try {
//			biz.register(mn);
//			Util.saveFile(p.getMf(),admindir,userdir);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return "redirect:select";
//	}
	
}
