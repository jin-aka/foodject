package com.foodject.vo;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

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
public class HostShopVO {
	private String id;
	private String mid;
	private String name;
	private String bnum;
	private String addr;
	private String addrd;
	private String phon;
	private String logo;
	private String info;
	private Date date;
	private int allmin;
	private int status;
	private int marker;
	private MultipartFile mf;
	private MultipartHttpServletRequest  mfsr;
	private MultipartFile bnumcheck;
	private double latt;
	private double logt;
	private int ordersstatus;
	private int allprice;
	

	


	public HostShopVO(String id, String mid, String name, String logo, int status, int orderstatus, int allprice) {
		this.id = id;
		this.mid = mid;
		this.name = name;
		this.logo = logo;
		this.status = status;
		this.ordersstatus = orderstatus;
		this.allprice = allprice;
	}





	public HostShopVO(String id, String mid, String name, String bnum, String addr, String addrd, String phon,
			String logo, String info, int allmin, int status, int marker, MultipartHttpServletRequest  mfsr, double latt, double logt) {
		this.id = id;
		this.mid = mid;
		this.name = name;
		this.bnum = bnum;
		this.addr = addr;
		this.addrd = addrd;
		this.phon = phon;
		this.logo = logo;
		this.info = info;
		this.allmin = allmin;
		this.status = status;
		this.marker = marker;
		this.mfsr = mfsr;
		this.latt = latt;
		this.logt = logt;
	}





	public HostShopVO(String id, String mid, String name, String bnum, String addr, String addrd, String phon,
			String logo, String info, Date date, int allmin, int status, int marker) {
		this.id = id;
		this.mid = mid;
		this.name = name;
		this.bnum = bnum;
		this.addr = addr;
		this.addrd = addrd;
		this.phon = phon;
		this.logo = logo;
		this.info = info;
		this.date = date;
		this.allmin = allmin;
		this.status = status;
		this.marker = marker;
	}


	
	
	
	public HostShopVO(String name, String bnum, String addr, String addrd, String phon, String info, int allmin,
			MultipartFile mf) {
		this.name = name;
		this.bnum = bnum;
		this.addr = addr;
		this.addrd = addrd;
		this.phon = phon;
		this.info = info;
		this.allmin = allmin;
		this.mf = mf;
	}
	

	public HostShopVO(String name, String bnum, String addr, String addrd, String phon, String info, int allmin,
			MultipartFile mf, double latt, double logt) {
		this.name = name;
		this.bnum = bnum;
		this.addr = addr;
		this.addrd = addrd;
		this.phon = phon;
		this.info = info;
		this.allmin = allmin;
		this.mf = mf;
		this.latt = latt;
		this.logt = logt;
	}
	

	public HostShopVO(String mid, String name, String bnum, String addr, String addrd, String phon, String info,
			int allmin, int status, int marker) {
		this.mid = mid;
		this.name = name;
		this.bnum = bnum;
		this.addr = addr;
		this.addrd = addrd;
		this.phon = phon;
		this.info = info;
		this.allmin = allmin;
		this.status = status;
		this.marker = marker;
	}


	public HostShopVO(double latt, double logt) {
		this.latt = latt;
		this.logt = logt;
	}


	public HostShopVO(MultipartFile bnumcheck) {
		this.bnumcheck = bnumcheck;
	}


	public HostShopVO(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	


}


