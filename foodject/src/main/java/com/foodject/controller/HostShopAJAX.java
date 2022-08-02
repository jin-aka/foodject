package com.foodject.controller;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Iterator;

import javax.servlet.http.HttpSession;

import com.foodject.biz.HostShopBiz;
import com.foodject.frame.Util;
import com.foodject.restapi.NaverORC;
import com.foodject.vo.HostShopVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@RestController
@RequestMapping("/host/shop")
public class HostShopAJAX {

	@Autowired
	HostShopBiz sbiz;
	@Autowired
	NaverORC orc;
	@Autowired
	Util ut;
	

	
	@RequestMapping("shopdelete")
	public void shopdelete( int[] sArray  ) {
		// HostManagerVO manager = (HostManagerVO) session.getAttribute("loginshop");
		System.out.println("shopdelete start : " + sArray[0]);

		for (int i = 0; i < sArray.length; i++) {
			System.out.println("sArray : " + sArray[i]);
		}
			try {
				
				for (int i = 0; i < sArray.length; i++) {
					sbiz.remove(sArray[i]);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}


	@RequestMapping("shopModify")
	public void shopModify( MultipartHttpServletRequest  mfsr,  HostShopVO obj  ) {
		// HostManagerVO manager = (HostManagerVO) session.getAttribute("loginshop");
		System.out.println("shopModify start : ");
		MultipartFile mf = null;
		String filename = "";
		Iterator<String> it = mfsr.getFileNames();
		while( it.hasNext() ){
		filename = it.next();
		mf = mfsr.getFile(filename);
		}
		if( mf == null ){
			System.out.println("mf null ");
		}else{
			ut.saveFile(mf, obj.getLogo(), "shop");

		}
		System.out.println("mf : "+mf);
		System.out.println("filename : "+filename);
		System.out.println("HostShopVO : " + obj);
		System.out.println("shopModify name end ");
		
		try {
			sbiz.modify(obj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("end obj; " + obj);
	}
	@RequestMapping("shopinfo")
	public Object shopinfo(int  id) {
		// HostManagerVO manager = (HostManagerVO) session.getAttribute("loginshop");
		System.out.println("shopinfo id : " + id);
		HostShopVO obj = null;
		try {
			obj = sbiz.get(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}
	@RequestMapping("checkbnum")
	public Object checkbnum(MultipartHttpServletRequest  mfsr, HttpSession session) {
		// HostManagerVO manager = (HostManagerVO) session.getAttribute("loginshop");
		Object obj = null;
		if( mfsr == null ){
			System.out.println(mfsr);

			return "0";
		}
		MultipartFile bnumcheck = null;
		String filename = "";
		Iterator<String> it = mfsr.getFileNames();
		while( it.hasNext() ){
			filename = it.next();
			bnumcheck = mfsr.getFile(filename);
		}
		byte [] data;

		String filePath = "";
		String savename = bnumcheck.getOriginalFilename();
		
		String[] splitname = savename.split("[.]");


		System.out.println(splitname.length);
		savename = "bnumimg" +  "." + splitname[splitname.length - 1];
		if (System.getProperty("os.name").toLowerCase().startsWith("win")){
			// 파일이 저장 안되시면 user.dir 뒤에 foodject를 지우거나 추가하세요!! 본인 경로에 맞게 설정해야됩니다 꼭 foodject가 아닐수도있어요
            filePath = Paths.get(System.getProperty("user.dir"), "foodject","src", "main","resources","static","foodject" ).toString() 
				+File.separator+ savename;
        } else{
            filePath = "/root/apache-tomcat-8.5.27/webapps/ROOT/WEB-INF/classes/static/foodject/"+ savename;
        }

		
		// System.out.println("Os name : "+System.getProperty("os.name").toLowerCase().startsWith("win"));
        System.out.println("path Result : " + filePath);

		try {
			data = bnumcheck.getBytes();
			FileOutputStream fo =
					new FileOutputStream(filePath);
			fo.write(data);
			fo.close();
			obj = orc.checkNaverORC(filePath);
			

		} catch (IOException e) {
			
		}

		return obj;
	}
	

}

