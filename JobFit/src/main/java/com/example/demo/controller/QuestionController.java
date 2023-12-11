package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Answers;
import com.example.demo.entity.Question;
import com.example.demo.service.ScoringService;

@Controller
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    private ScoringService scoringService;

    private static final int TOTAL_QUESTIONS = 16;

    // 初期質問を表示
    @GetMapping("/initial")
    public String showInitialQuestions(Model model) {
        scoringService.initializeTypeQuestions();
        Question initialQuestion = scoringService.getNextTypeQuestion("Type1"); // 例: 最初の質問をType1から取得
        model.addAttribute("question", initialQuestion);
        model.addAttribute("questionNumber", 1);
        return "initial-question"; // HTMLビューの名前を返す
    }

    // ユーザーの回答を受けてスコアを計算
    @PostMapping("/answer")
    public String answerQuestion(@RequestBody Answers userAnswer, Model model) {
        scoringService.calculateScores(List.of(userAnswer));
        int currentQuestionNumber = Integer.parseInt(model.getAttribute("questionNumber").toString());
        
        if (currentQuestionNumber < TOTAL_QUESTIONS) {
            // 次の質問があれば表示
            Question nextQuestion = scoringService.getNextTypeQuestion("Type1"); // 例: 次の質問をType1から取得
            model.addAttribute("question", nextQuestion);
            model.addAttribute("questionNumber", currentQuestionNumber + 1);
            return "next-question"; // HTMLビューの名前を返す
        } else {
            // 質問が終了した場合は最終結果画面にリダイレクトなどの処理が必要
            return "redirect:/questions/result";
        }
    }

    // 最終的な結果を表示
    @GetMapping("/result")
    public String showFinalResult(Model model) {
        String finalResult = scoringService.getFinalResult();
        model.addAttribute("result", finalResult);
        return "final-result"; // HTMLビューの名前を返す
    }
}
