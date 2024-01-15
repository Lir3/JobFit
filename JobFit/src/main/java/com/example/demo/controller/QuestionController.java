package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.entity.Answers;
import com.example.demo.entity.Question;
import com.example.demo.service.ScoringService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    private ScoringService scoringService;

    private static final int TOTAL_QUESTIONS = 24;
    

    @RequestMapping(path = "/initial", method = RequestMethod.GET)
    public String showInitialQuestions(Model model) {
        scoringService.initializeTypeQuestions();
        Question initialQuestion = scoringService.getNextRandomQuestion();
        model.addAttribute("question", initialQuestion);
        model.addAttribute("questionNumber", 1);
        return "initialquestion";
    }
    
    

    @RequestMapping(path = "/answer", method = RequestMethod.POST)
    public String answerQuestion(@ModelAttribute("questionNumber") int questionNumber,
                                 @ModelAttribute("userAnswer") Answers userAnswer,
                                 Model model, HttpSession session) {
        try {
            scoringService.calculateScores(List.of(userAnswer));

            if (questionNumber < TOTAL_QUESTIONS) {
                Question nextQuestion = scoringService.getNextRandomQuestion();
                model.addAttribute("question", nextQuestion);
                model.addAttribute("questionNumber", questionNumber + 1);
                return "nextquestion";
            } else {
                return "redirect:/questions/result";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    @RequestMapping(path = "/result", method = RequestMethod.GET)
    public String showFinalResult(Model model, HttpSession session) {
        String finalResult = scoringService.getFinalResult();
        model.addAttribute("result", finalResult);
        session.setAttribute("result", finalResult);
        return "finalresult";
    }
}
