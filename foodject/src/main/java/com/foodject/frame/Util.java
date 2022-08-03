package com.foodject.frame;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;

import com.foodject.restapi.NaverObj;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class Util {

	@Autowired
	NaverObj nobj;

	// 파일, 저장할 이름, 사용되는 디비 테이블명
	public void saveFile(MultipartFile mf, String savename, String table) {
		byte [] data;
		
		//이미지 경로설정
		//String pimgpath = Paths.get(System.getProperty("user.dir"), "src", "main","resources","static","custimg").toString();
		String filePath = "";
		
		if (System.getProperty("os.name").toLowerCase().startsWith("win")){
			// 파일이 저장 안되시면 user.dir 뒤에 foodject를 지우거나 추가하세요!! 본인 경로에 맞게 설정해야됩니다 꼭 foodject가 아닐수도있어요
            filePath = Paths.get(System.getProperty("user.dir"), "foodject", "src", "main","resources","static","foodject", table ).toString() 
				+ File.separator + savename;
        } else{
            filePath = "/root/apache-tomcat-8.5.27/webapps/ROOT/WEB-INF/classes/static/foodject/"+table + File.separator + savename;
        }

		
		// System.out.println("Os name : "+System.getProperty("os.name").toLowerCase().startsWith("win"));
        System.out.println("path Result : " + filePath);

		try {
			data = mf.getBytes();
			FileOutputStream fo =
					new FileOutputStream(filePath);
			fo.write(data);
			fo.close();

			nobj.upLoad( mf , filePath , savename, table);
			
		} catch (IOException e) {
			
		}

	}

}
