package com.foodject.vo;

import java.util.Date;

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
public class UserMenuVO {
	private int id;
	private int sid;
	private int cid;
	private int collid;
	private String name;
	private int price;
	private int line;
	private Date date;
	
	private String category;
	private String img;
	
	
	
	//for update(4)
	public UserMenuVO(int id, int collid, String name, int price) {
		this.id = id;
		this.collid = collid;
		this.name = name;
		this.price = price;
	}
	
	//for updateLine(2)
	public UserMenuVO(int id, int line) {
		this.id = id;
		this.line = line;
	}

	//for insert(6)
	public UserMenuVO(int sid, int cid, int collid, String name, int price, int line) {
		this.sid = sid;
		this.cid = cid;
		this.collid = collid;
		this.name = name;
		this.price = price;
		this.line = line;
	}
	
	
	
}


