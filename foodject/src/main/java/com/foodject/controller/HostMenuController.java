package com.foodject.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.foodject.biz.CateBiz;
import com.foodject.biz.HostCollectionBiz;
import com.foodject.biz.HostImgBiz;
import com.foodject.biz.HostMenuBiz;
import com.foodject.biz.HostShopBiz;
import com.foodject.frame.Util;
import com.foodject.vo.CateVO;
import com.foodject.vo.HostCollectionVO;
import com.foodject.vo.HostImgVO;
import com.foodject.vo.HostManagerVO;
import com.foodject.vo.HostMenuVO;
import com.foodject.vo.HostShopVO;

@Controller
@RequestMapping("/host/menu")
public class HostMenuController {
	
	@Autowired
	HostShopBiz biz;
	@Autowired
	HostCollectionBiz cbiz;
	@Autowired
	HostMenuBiz mbiz;
	
	@Autowired
	HostImgBiz ibiz;
	
	@Autowired
	CateBiz ctbiz;
	
	@Autowired
	Util util;
	
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
	public String delete(Model m, int[] sArray, int id, int collid, int sid) {

		
		try {
			for (int i = 0; i < sArray.length; i++) {
				
				mbiz.remove(sArray[i]);
			}
		} catch (Exception e) {
			e.printStackTrace();
}
		
		return "redirect:/host/menu/msel?id="+id+"&collid="+collid+"&sid="+sid;
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
		List<CateVO> ctlist = null;
		try {
			mnlist = mbiz.getmenu(collid);
			mv.addObject("mnlist",mnlist);
			
			col = cbiz.get(id);
			mv.addObject("col",col);
			
			ctlist = ctbiz.get();
			mv.addObject("ctlist", ctlist);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mv.setViewName("/host/index");
		mv.addObject("center", "/host/menu/register");
		return mv;
	}
	@RequestMapping("/colregister")
	public ModelAndView colregister(ModelAndView mv, int id, int sid) {
		HostShopVO sh = null;
		List<HostCollectionVO> clist = null;
		try {
			clist = cbiz.get_byShop(sid);
			mv.addObject("clist",clist);
			
			sh = biz.get(id);
			mv.addObject("sh",sh);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mv.setViewName("/host/index");
		mv.addObject("center", "/host/menu/colregister");
		return mv;
	}
	@RequestMapping("colregister/colregisterimpl")
	public String registerimpl(Model m, HostCollectionVO cov) {
	
		try {
			
			
			cbiz.register(cov);
			System.out.println("register OK");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return "redirect:/host/menu/col?id="+cov.getSid()+"&sid="+cov.getSid();
	}
	@RequestMapping("register/menuregisterimpl")
	public String menuregisterimpl(Model m, HostMenuVO mnv) {
		HostImgVO iv = new HostImgVO();
		String imgname = mnv.getMf().getOriginalFilename();
		String[] splitname = imgname.split("[.]");
		iv.setImg(imgname);
		try {
			mbiz.register(mnv);
			String savename = mnv.getId() + "." + splitname[splitname.length -1];
			iv.setImg(savename);
			iv.setOutid(String.valueOf(mnv.getId()));
			iv.setTable("menu");
			ibiz.register(iv);
			System.out.println(iv);
			util.saveFile(mnv.getMf(), savename, "menu" );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/host/menu/msel?id="+mnv.getCollid()+"&collid="+mnv.getCollid()+"&sid="+mnv.getSid();
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
