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
public class LoginController {
	@Autowired
	private AccountRepository accountRepository;
	

    @Autowired
    private HttpSession httpSession; // HttpSessionをDI

	@RequestMapping(path = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}
	

	@RequestMapping(path = "/loginpage", method = RequestMethod.GET)
    public String showLoginPage() {
        return "loginpage";
    }
	
	@RequestMapping(path = "/test", method = RequestMethod.GET)
    public String test() {
        return "test";
	}
	
	@RequestMapping(path = "/test2", method = RequestMethod.GET)
    public String test2() {
        return "test2t";
	}
	
	@RequestMapping(path = "/loginpage", method = RequestMethod.POST)
    public String login(String username, String password, Model model) {
        Account user = accountRepository.findByUsernameAndPassword(username, password);
        if (user != null) {
            // ログイン成功
            if ("admin".equals(username) && "admin".equals(password)) {
                // 管理者ユーザーの場合は管理者画面に遷移
                return "redirect:/test";
            } else {
                // 一般ユーザーの場合は診断画面に遷移
                return "test2";
            }
        } else {
            // ログイン失敗
            // ログイン失敗時の処理
            model.addAttribute("error", "ユーザー名またはパスワードが正しくありません。");
            return "loginpage";
        }
    }
	
	
	@RequestMapping(path = "/guest-login", method = RequestMethod.GET)
	public String guestLogin(Model model) {
		// ゲストユーザーのデータを指定してログイン
		Account guestUser = accountRepository.findByUsername("guest");
		if (guestUser != null) {
			// ゲストユーザーとしてログイン成功
			return "redirect:/home";
		} else {
			// ゲストユーザーが存在しない場合の処理
			model.addAttribute("error", "ゲストユーザーが存在しません。");
			return "redirect:/ng";
		}
		
	}
	
}