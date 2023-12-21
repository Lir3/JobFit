package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.entity.Account;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

 


 // HomeController.java
    @RequestMapping(path = "/home", method = RequestMethod.GET)
    public String home(HttpSession session, Model model) {
        
        return "home";
    }

    @RequestMapping(path = "/dashboard", method = RequestMethod.GET)
    public String dashboard(HttpSession session, Model model) {
        // セッションから診断の結果を取得
        if (session.getAttribute("loggedInUser") != null) {
            Account loggedInUser = (Account) session.getAttribute("loggedInUser");

            // ゲストユーザーでない場合のみ処理を行う
            if (!"guest".equals(loggedInUser.getUsername())) {
                String diagnosisResult = (String) session.getAttribute("result");

                if (diagnosisResult != null) {
                    model.addAttribute("result", diagnosisResult);
                }

                // セッションから結果を削除
                session.removeAttribute("diagnosisResult");
            }
        }

        return "dashboard";
    }



}
