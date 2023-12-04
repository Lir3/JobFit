package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.entity.Account;
import com.example.demo.repository.AccountRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

	@Autowired
    private AccountRepository accountRepository;

	@RequestMapping(path = "/home", method = RequestMethod.GET)
    public String home() {
        return "home";
    }

	@RequestMapping(path = "/start", method = RequestMethod.GET)
    public String start1(String username, String password, Model model, HttpSession session) {
        // ログインしている場合、診断の結果をセッションで保持
    	Account user = accountRepository.findByUsernameAndPassword(username, password);
        if (user != null) {
            String diagnosisResult = (String) session.getAttribute("diagnosisResult");
            if (diagnosisResult != null) {
                model.addAttribute("previousResult", diagnosisResult);
            }
        }

        return "start";
    }

  

	@RequestMapping(path = "/start", method = RequestMethod.POST)
    public String start(Model model, HttpSession session) {
        // 診断の結果をセッションに保存
        String result = "Your diagnosis result"; // ここで実際の診断結果を取得
        session.setAttribute("diagnosisResult", result);

        return "redirect:/dashboard";
    }

	@RequestMapping(path = "/dashboard", method = RequestMethod.GET)
    public String dashboard(Model model, HttpSession session) {
        // セッションから診断の結果を取得
        String diagnosisResult = (String) session.getAttribute("diagnosisResult");
        if (diagnosisResult != null) {
            model.addAttribute("result", diagnosisResult);
        }

        return "dashboard";
    }

  
}
