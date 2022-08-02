package com.foodject.vo;

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
public class HostCollectionVO {
	private int id;
	private String name;
	private int line;
	private int sid;
	
	
	public HostCollectionVO(int id, int line) {
		this.id = id;
		this.line = line;
	}


	public HostCollectionVO(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}


	public HostCollectionVO(String name, int line, int sid) {
		super();
		this.name = name;
		this.line = line;
		this.sid = sid;
	}
	
	
}


