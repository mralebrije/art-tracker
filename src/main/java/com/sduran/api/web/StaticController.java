package com.sduran.api.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StaticController extends BaseController {
	
	@RequestMapping("/")
	public String index() {
		return "index";
	}
}
