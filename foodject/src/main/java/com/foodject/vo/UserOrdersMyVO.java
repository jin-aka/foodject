package com.foodject.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserOrdersMyVO {
	
	//orders
	private int oid;
	private String uid;
	private String addr;
	private String addrd;
	private String phon;
	private String nick;
	private Date odate;	
	private int status;
	private String ask;
	
	//orders detail
	private int deid;
	private int mid;
	private String mname;	
	private int mnum;	
	private int mprice;	
	
	//orders option detail
	private int opid;
	private String opname;
	private int opprice;
	
	//shop
	private String sname;
	private String slogo;




	//delete
	public UserOrdersMyVO(String uid, int status) {
		this.uid = uid;
		this.status = status;
	}

	//selectmy
	public UserOrdersMyVO(String uid, Date odate, String sname, int oid, String slogo) {
		this.uid = uid;
		this.odate = odate;
		this.sname = sname;
		this.slogo = slogo;
		this.oid = oid;
	}

	//selectmenu - allargs


	
	
}
