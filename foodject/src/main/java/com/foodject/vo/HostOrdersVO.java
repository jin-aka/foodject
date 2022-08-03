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
public class HostOrdersVO {
	private int id;
	private String uid;
	private int sid;
	private String addr;
	private String addrd;
	private String phon;
	private String nick;
	private Date date;
	private int status;
	private String ask;
	private int detail_id;
	private int detail_odid;
	private int detail_mnid;
	private String detail_mname;
	private int detail_num;
	private int detail_price;
	private int dopt_did;
	private int dopt_opid;
	private String dopt_oname;
	private int dopt_oprice;
	private int allprice;
	public HostOrdersVO(int id) {
		this.id = id;
	}



	

	


	
	


}


