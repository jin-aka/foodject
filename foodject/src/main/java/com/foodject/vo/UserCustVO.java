package com.foodject.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserCustVO {
	private String id;
	private String pwd;
	private String nick;
	private String name;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birth;
	private Date date;
	private String phon;
	private String addr;
	private String addrd;
	private int status;
	private String img;
	private MultipartFile mf;
	
	public UserCustVO(String id, String pwd, String name) {
		this.id = id;
		this.pwd = pwd;
		this.name = name;
	}
	public UserCustVO(String id, String pwd, String name, Date birth, String phon, String addr, String addrd) {
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.birth = birth;
		this.phon = phon;
		this.addr = addr;
		this.addrd = addrd;
	}

	public UserCustVO(String id, String pwd, String nick, String name, Date birth, String phon, String addr, String addrd, int status,
			MultipartFile mf) {
		this.id = id;
		this.pwd = pwd;
		this.nick = nick;
		this.name = name;
		this.birth = birth;
		this.phon = phon;
		this.addr = addr;
		this.addrd = addrd;
		this.status = status;
		this.mf = mf;
	}
	public UserCustVO(String id, String pwd, String nick, String name, Date birth, String phon, String addr, String addrd, int status) {
		this.id = id;
		this.pwd = pwd;
		this.nick = nick;
		this.name = name;
		this.birth = birth;
		this.phon = phon;
		this.addr = addr;
		this.addrd = addrd;
		this.status = status;
	}
	public UserCustVO(String id, String pwd, String nick, String name, Date birth, String phon, String addr, String addrd, int status,
			String img) {
		this.id = id;
		this.pwd = pwd;
		this.nick = nick;
		this.name = name;
		this.birth = birth;
		this.phon = phon;
		this.addr = addr;
		this.addrd = addrd;
		this.status = status;
		this.img = img;
	}
	public UserCustVO(String id, int status) {
		this.id = id;
		this.status = status;
	}

	
}


