package com.foodject.vo;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class UserOptcartVO {
	private int ctid;
	private int oid;
	private String uid;
	private int price;
	private String name;
	
	// for insert(2)
	public UserOptcartVO(int ctid, int oid) {
		this.ctid = ctid;
		this.oid = oid;
	}
	
	
	

	
	
}