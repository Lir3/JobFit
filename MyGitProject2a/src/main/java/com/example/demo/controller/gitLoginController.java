package com.example.demo.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class	gitLoginController {

	@RequestMapping(path = "/gitpralogin", method = RequestMethod.GET)
    public String login() {
        return "gitlogin";
    }

	@RequestMapping(path = "/gitpralogin", method = RequestMethod.POST)
    public String gitlogin(String id, String password, Model model) {
        if ("gitlogin".equals(id) && "gitpw".equals(password)) {
            return "gititemlist";
        } else {
            return "gitlogin";
        }
    }
 }
