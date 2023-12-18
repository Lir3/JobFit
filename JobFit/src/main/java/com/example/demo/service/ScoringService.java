package com.example.demo.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Answers;
import com.example.demo.entity.Jobs;
import com.example.demo.entity.Question;
import com.example.demo.repository.AnswersRepository;
import com.example.demo.repository.JobsRepository;
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
    
    @Autowired
    private JobsRepository jobsRepository;


    // 各タイプのスコアを保持するマップ
    private Map<String, Integer> typeScores = new HashMap<>();

    // すべての質問を保持するリスト
    private List<Question> allQuestions = new ArrayList<>();

    // ユーザの回答に基づいて各タイプのスコアを計算
    public void calculateScores(List<Answers> userAnswers) {
        if (typeScores.isEmpty()) {
            initializeTypeScores();
        }

        for (Answers answer : userAnswers) {
            Question question = questionRepository.findById(answer.getQuestion()).orElse(null);
            if (question != null) {
                String typeName = question.getType().getName();
                int answerScore = getAnswerScore(question, answer.getChoice());
                // タイプのスコアに回答のポイントを加算
                typeScores.put(typeName, typeScores.getOrDefault(typeName, 0) + answerScore);
           
                // ポイントの加算情報をコンソールに出力
                System.out.println("質問ID: " + question.getId() + ", タイプ: " + typeName + ", 回答: " + answer.getChoice() + ", ポイント: " + answerScore);
                
                
            } else {
                // エラーハンドリング: question が見つからなかった場合の処理
                // 例: ログを出力して通知する、エラーページにリダイレクトする など

                // その他のエラーハンドリングの処理を追加
            }
        }
    }

    // タイプごとのスコアを初期化
    private void initializeTypeScores() {
        // 使用可能なすべてのタイプを取得
        List<String> availableTypes = typesRepository.findAllTypeNames();

        // タイプごとに初期スコアを設定
        for (String typeName : availableTypes) {
            typeScores.put(typeName, 0);
        }
    }

    // すべての質問を取得してシャッフル
    private void initializeAllQuestions() {
        allQuestions = questionRepository.findAll();
        Collections.shuffle(allQuestions);
    }

    // ランダムな質問を取得
    public Question getNextRandomQuestion() {
        // 質問がすべて使用された場合は null を返す
        if (allQuestions.isEmpty()) {
            return null;
        }

        // 質問をシャッフルして最初の質問を取得
        Question nextQuestion = allQuestions.remove(0);

        return nextQuestion;
    }

    public String getFinalResult() {
        // 各タイプのスコアを比較して最終的な結果を返すロジックを実装
        // ここでは例として最もスコアの高いタイプを取得
        String finalType = findMaxScoreType();
        if (finalType != null) {
            // タイプに対応するおすすめの職種を取得
            List<Jobs> recommendedJobs = getRecommendedJobByType(finalType);

            // タイプに対応する職種の名前を取得
            List<String> recommendedJobNames = recommendedJobs.stream()
                    .map(Jobs::getName)
                    .collect(Collectors.toList());
            
            String type = "";
			if ("冒険家タイプ".equals(finalType)) {
				type = "冒険家タイプの人は、新しいことに挑戦することが好きで、自ら考え行動することが得意です。そのため、新しい技術やトレンドを常に学びながら、複雑な問題を解決する能力が求められる職種に向いています。また、自ら考え行動することが求められる職種にも向いています。\n";
			} else if ("協調性タイプ".equals(finalType)) {
				type = "協調性の高い人は、人と協力して仕事をすることが得意です。そのため、トラブルが発生した際に、冷静に対応し、チームで協力して仕事をする必要がある職種に向いています。また、人とコミュニケーションをとることが求められる職種にも向いています。\n";
			} else if ("几帳面タイプ".equals(finalType)) {
				type = "几帳面な人は、細かい作業を丁寧に行うことが得意です。そのため、データの管理や、ソフトウェアのテストなど、細かい作業が求められる職種に向いています。また、責任感を持って仕事をすることが求められる職種にも向いています。\n";
			} else if ("論理的タイプ".equals(finalType)) {
				type = "論理的に思考することが得意な人は、複雑な問題を論理的に解く能力が求められる職種に向いています。また、新しい技術やトレンドを常に学ぶ必要がある職種にも向いています。";
			} else if ("創造性タイプ".equals(finalType)) {
				type = "創造性の高い人は、新しいアイデアを生み出すことが得意です。そのため、ユーザーのニーズを理解して、新しいサービスを開発する必要がある職種に向いています。また、表現力や発想力が求められる職種にも向いています。";
			} else if ("コミュニケーションタイプ".equals(finalType)) {
				type = "人とコミュニケーションをとることが得意な人は、ビジネス目標を達成するために、さまざまな人と協力する必要がある職種に向いています。また、プレゼンテーションや交渉などのコミュニケーション能力が求められる職種にも向いています。\n";
			} else if ("責任感タイプ".equals(finalType)) {
				type = "「責任感を持って仕事をすることが得意な人は、重要な役割を担う必要がある職種に向いています。また、目標を達成するために、計画的に仕事を進めることができる職種にも向いています。\n";
			} else if ("目標達成意欲タイプ".equals(finalType)) {
				type = "目標達成意欲が高い人は、目標を達成するために、努力を惜しまない職種に向いています。また、新しい技術やトレンドを常に学びながら、自分のスキルを向上させることができる職種にも向いています。";
			} else {
			    // 未知のタイプの場合の処理
				type = "未知のタイプです。";
			}

            // タイプに対応する職種を取得
			return "あなたのタイプは「" + finalType + "」です。\n" + type + "おすすめの職種は「" + String.join(", ", recommendedJobNames) + "」です。";
        } else {
            return "結果が見つかりませんでした。";
        }
    }
    
 // タイプに対応するおすすめの職種を取得
    private List<Jobs> getRecommendedJobByType(String type) {
    	 // タイプ名から typeId を取得
        Integer typeId = typesRepository.findTypeIdByName(type);

            if (typeId != null) {
                // typeId に対応するおすすめの職種をデータベースから取得
                return typesRepository.findRecommendedJobsByTypeId(typeId);
            }

        // 該当するおすすめの職種が見つからない場合は空のリストを返す
        return Collections.emptyList();
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
        List<String> maxTypes = new ArrayList<>();
        int maxScore = Integer.MIN_VALUE;

        for (Map.Entry<String, Integer> entry : typeScores.entrySet()) {
            int currentScore = entry.getValue();

            if (currentScore > maxScore) {
                // より高いスコアのタイプが見つかった場合、リストをクリアして現在のタイプを追加
                maxTypes.clear();
                maxTypes.add(entry.getKey());
                maxScore = currentScore;
            } else if (currentScore == maxScore) {
                // 同じスコアのタイプが見つかった場合、リストに追加
                maxTypes.add(entry.getKey());
            }
        }

        // maxTypes からランダムに選択
        if (!maxTypes.isEmpty()) {
            Collections.shuffle(maxTypes);
            return maxTypes.get(0);
        }

        return null;
    }
    
    // タイプごとの質問を初期化
    public void initializeTypeQuestions() {
        initializeAllQuestions();
    }

    // タイプごとの次の質問を取得
    public Question getNextTypeQuestion(String typeName) {
        // タイプが一致する質問を検索して返す
        return allQuestions.stream()
                .filter(question -> typeName.equals(question.getType().getName()))
                .findFirst()
                .orElse(null);
    }
}
