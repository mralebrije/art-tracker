package com.sduran.api;

import com.sduran.api.web.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StaticController extends BaseController {
	
	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@RequestMapping("/action")
	public String action() {
		return "action-buttons";
	}

	@RequestMapping("/edit")
	public String edit() {
		return "museum-edit-modal";
	}

	@RequestMapping("/add")
	public String add() {
		return "museum-add-modal";
	}

}
