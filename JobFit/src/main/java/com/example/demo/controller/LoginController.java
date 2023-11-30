package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.entity.Account;
import com.example.demo.repository.AccountRepository;

@Controller
public class LoginController {
    @Autowired
    private AccountRepository accountRepository;

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(String username, String password, Model model) {
        // ユーザー名とパスワードを使ってユーザーを検索
        Account user = accountRepository.findByUsernameAndPassword(username, password);
        
        if (user != null) {
            // ログイン成功
            return "redirect:/home"; // ホーム画面にリダイレクト
        } else {
            // ログイン失敗
            // ログイン失敗時の処理
            model.addAttribute("error", "ユーザー名またはパスワードが正しくありません。");
            return "redirect:/ng"; // エラー画面にリダイレクト
        }
    }

    @RequestMapping(path = "/guest-login", method = RequestMethod.GET)
    public String guestLogin(Model model) {
        // ゲストユーザーのデータを指定してログイン
        Account guestUser = accountRepository.findByUsername("guest");

        if (guestUser != null) {
            // ゲストユーザーとしてログイン成功
            return "redirect:/home"; // ホーム画面にリダイレクト
        } else {
            // ゲストユーザーが存在しない場合の処理
            model.addAttribute("error", "ゲストユーザーが存在しません。");
            return "redirect:/ng"; // エラー画面にリダイレクト
        }
    }
}
