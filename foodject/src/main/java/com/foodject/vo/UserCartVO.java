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
public class UserCartVO {
	private int id;
	private String uid;
	private int mnid;
	private int num;
	
	// For insert
	public UserCartVO(String uid, int mnid, int num) {
		this.uid = uid;
		this.mnid = mnid;
		this.num = num;
	}
	
	// For updateCnt
	public UserCartVO(int id, int num) {
		this.id = id;
		this.num = num;
	}

	// For selectForInsert
	public UserCartVO(String uid, int mnid) {
		this.uid = uid;
		this.mnid = mnid;
	}

	
	
	
	
}