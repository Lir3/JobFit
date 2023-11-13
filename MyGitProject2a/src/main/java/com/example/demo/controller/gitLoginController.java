package com.example.demo.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class	gitLoginController {

    @RequestMapping("/gitpralogin")
    public String login() {
        return "gitlogin";
    }

    @PostMapping("/gitpralogin")
    public String gitlogin(String id, String password, Model model) {
        if ("gitlogin".equals(id) && "gitpw".equals(password)) {
            return "gititemlist";
        } else {
            return "gitlogin";
        }
    }
 }
