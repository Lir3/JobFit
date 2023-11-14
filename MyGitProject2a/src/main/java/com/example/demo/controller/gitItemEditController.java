package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class gitItemEditController {

	@RequestMapping(path = "/gititemedit", method = RequestMethod.GET)
	public String gititemedit() {
		return "gititemedit";
	}

	@RequestMapping(path = "/itemedit2", method = RequestMethod.POST)
	public String modelEdit(Model model, String syouhinmei, String nedann) {
		String modelEdit = syouhinmei;
		String modelNedan = nedann;

		model.addAttribute("hituma", modelEdit);
		model.addAttribute("hituma2", modelNedan);

		return "gititemedit2";
	}
}
