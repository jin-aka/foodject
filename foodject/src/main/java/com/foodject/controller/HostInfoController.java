package com.foodject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/host/info")
public class HostInfoController {

	@RequestMapping("")
	public String orders(Model m) {
		m.addAttribute("center", "host/info/center");
		return "host/index";
	}

}
