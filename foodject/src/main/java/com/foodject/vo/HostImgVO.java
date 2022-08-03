package com.foodject.vo;


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
public class HostImgVO {

	private int id;
	private String outid;
	private String table;
	private String img;
	private MultipartFile mf;
}
