package com.example.demo.controller;

import java.util.List;
import java.util.Scanner;

import com.example.demo.dao.QuestionDAO;
import com.example.demo.entity.Question;

public class QuizController {
	public static Question quiz;
	public Scanner scanner;

	public QuizController(Question quiz) {
		QuizController.quiz = quiz;
		this.scanner = new Scanner(System.in);
	}

	public void runQuiz() {
		int totalScore = 0;

		for (Question question : quiz.getQuestions()) {
			displayQuestion(question);
			int userAnswer = getUserAnswer();

			if (userAnswer == question.getAnswer()) {
				totalScore += 1;
			}
		}

		displayQuizResult(totalScore);
	}

	private void displayQuestion(Question question) {
		System.out.println(question.getText());
		System.out.println("1: はい, 2: いいえ, 3: どちらでもない");
	}

	private int getUserAnswer() {
		return scanner.nextInt();
	}

	private void displayQuizResult(int totalScore) {
		System.out.println("あなたの適性スコアは " + totalScore + " 点です。");
	}

	public static void main(String[] args) {
		List<Question> quizs = createQuizFromDatabase();
		QuizController quizController = new QuizController(null);
		quizController.runQuiz();
	}

	private static List<Question> createQuizFromDatabase() {
		QuestionDAO questionDAO = new QuestionDAO();
		List<Question> questions = questionDAO.getAllQuestions();
		//
		//		Question quiz = new Question(null, null, null, null);
		//		quiz.setQuestions(questions);

		return questions;
	}
}
