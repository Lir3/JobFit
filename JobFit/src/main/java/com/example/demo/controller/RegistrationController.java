package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.entity.Account;
import com.example.demo.entity.Account.Gender;
import com.example.demo.repository.AccountRepository;

@Controller
public class RegistrationController {

    @Autowired
    private AccountRepository accountRepository;

    @RequestMapping(path = "/register", method = RequestMethod.GET)
    public String register() {
        return "register"; // 新規登録画面に遷移
    }

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public String processRegistration(String username, String password, String mailaddress, String gender, int age, Model model) {
        // バリデーション
        if (username == null || username.trim().isEmpty() ||
            password == null || password.trim().isEmpty() ||
            mailaddress == null || mailaddress.trim().isEmpty() ||
            gender == null || gender.trim().isEmpty()) {
            model.addAttribute("error", "すべてのフィールドを入力してください。");
            return "register";
        }

        // 既存ユーザーの重複チェック
        if (accountRepository.existsByUsername(username)) {
            model.addAttribute("error", "指定されたユーザー名は既に使用されています。別のユーザー名を試してください。");
            return "register";
        }

        // 新規ユーザーを作成して保存
        Account newUser = new Account();
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setMailaddress(mailaddress);
        newUser.setGender(Gender.valueOf(gender.toUpperCase())); // 文字列からEnumに変換
        newUser.setAge(age);

        // Spring Data JPAが提供するsaveメソッドを使用して新規ユーザーを保存
        accountRepository.save(newUser);

        // 登録成功時の処理
        model.addAttribute("success", "新規登録が成功しました。");
        return "login"; // ログイン画面にリダイレクト
    }
}
