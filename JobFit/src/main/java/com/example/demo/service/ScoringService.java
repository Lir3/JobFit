package com.example.demo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Answers;
import com.example.demo.entity.Question;
import com.example.demo.repository.AnswersRepository;
import com.example.demo.repository.QuestionRepository;
import com.example.demo.repository.TypesRepository;

@Service
public class ScoringService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private TypesRepository typesRepository;

    @Autowired
    private AnswersRepository answersRepository;

    // 各タイプのスコアを保持するマップ
    private Map<String, Integer> typeScores = new HashMap<>();

    // タイプごとの質問リストを保持
    private Map<String, List<Question>> typeQuestions = new HashMap<>();

    // ユーザの回答に基づいて各タイプのスコアを計算
    public void calculateScores(List<Answers> userAnswers) {
        for (Answers answer : userAnswers) {
            Question question = questionRepository.findById(answer.getQuestion()).orElse(null);
            if (question != null) {
                String typeName = question.getType().getName();
                int answerScore = getAnswerScore(question, answer.getChoice());
                // タイプのスコアに回答のポイントを加算
                typeScores.put(typeName, typeScores.getOrDefault(typeName, 0) + answerScore);
            }
        }
    }

    // 最終的な結果を取得
    public String getFinalResult() {
        // 各タイプのスコアを比較して最終的な結果を返すロジックを実装
        // ここでは例として最もスコアの高いタイプを取得
        String finalType = findMaxScoreType();
        if (finalType != null) {
            // タイプに対応する職種を取得
            return "あなたに適した職種は " + finalType + " です。";
        } else {
            return "結果が見つかりませんでした。";
        }
    }

    // 回答の選択肢に対するポイントを取得
    private int getAnswerScore(Question question, String choice) {
        // 質問の選択肢ごとのポイントを返す
        switch (choice) {
            case "はい":
                return question.getYes();
            case "いいえ":
                return question.getNo();
            case "どちらでもない":
                return question.getNeutral();
            default:
                return 0;
        }
    }

    // 最大スコアのタイプを検索
    private String findMaxScoreType() {
        String maxType = null;
        int maxScore = Integer.MIN_VALUE;
        for (Map.Entry<String, Integer> entry : typeScores.entrySet()) {
            if (entry.getValue() > maxScore) {
                maxScore = entry.getValue();
                maxType = entry.getKey();
            }
        }
        return maxType;
    }

    // タイプごとの質問を初期化
    public void initializeTypeQuestions() {
        List<Question> allQuestions = questionRepository.findAll(); // データベースから全ての質問を取得
        for (Question question : allQuestions) {
            String typeName = question.getType().getName();
            typeQuestions.computeIfAbsent(typeName, k -> new ArrayList<>()).add(question);
        }
    }

    // タイプごとの次の質問を取得
    public Question getNextTypeQuestion(String typeName) {
        if (typeQuestions.containsKey(typeName) && !typeQuestions.get(typeName).isEmpty()) {
            return typeQuestions.get(typeName).remove(0);
        }
        return null; // もし質問が終了した場合は null を返すなどの処理が必要
    }
}
