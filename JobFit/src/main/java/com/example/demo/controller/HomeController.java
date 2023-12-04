package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.repository.AccountRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

    @Autowired
    private static AccountRepository accountRepository;

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/start")
    public String start1(String username, String password, Model model, HttpSession session) {
        // ログインしている場合、診断の結果をセッションで保持
        if (isUserLoggedIn(username, password)) {
            String diagnosisResult = (String) session.getAttribute("diagnosisResult");
            if (diagnosisResult != null) {
                model.addAttribute("previousResult", diagnosisResult);
            }
        }

        return "start";
    }

    private boolean isUserLoggedIn(String username, String password) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@PostMapping("/start")
    public String start(Model model, HttpSession session) {
        // 診断の結果をセッションに保存
        String result = "Your diagnosis result"; // ここで実際の診断結果を取得
        session.setAttribute("diagnosisResult", result);

        return "redirect:/dashboard";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, HttpSession session) {
        // セッションから診断の結果を取得
        String diagnosisResult = (String) session.getAttribute("diagnosisResult");
        if (diagnosisResult != null) {
            model.addAttribute("result", diagnosisResult);
        }

        return "dashboard";
    }

  
}
