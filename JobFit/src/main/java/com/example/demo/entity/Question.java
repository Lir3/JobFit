package com.example.demo.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "問題")
public class Question {

	@Id
	@Column(name = "Questions")
	public Question quiz;

	public Question getQuiz() {
		return quiz;
	}

	public void setQuiz(Question quiz) {
		this.quiz = quiz;
	}

	@Column(name = "QuestionID")
	private int questionID;

	@Column(name = "text")
	private String text;

	@Column(name = "choice")
	private String[] choice;

	@Column(name = "correctAnswerIndex")
	private int correctAnswerIndex;

	@Column(name = "Parameter")
	private int parameter;

	public Question(int questionID, String text, String[] choice, int correctAnswerIndex) {
		this.questionID = questionID;
		this.text = text;
		this.choice = choice;
		this.correctAnswerIndex = correctAnswerIndex;
	}

	public int getQuestionID() {
		return questionID;
	}

	public void setQuestionID(int questionID) {
		this.questionID = questionID;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String[] getChoice() {
		return choice;
	}

	public void setChoice(String[] choice) {
		this.choice = choice;
	}

	public int getCorrectAnswerIndex() {
		return correctAnswerIndex;
	}

	public void setCorrectAnswerIndex(int correctAnswerIndex) {
		this.correctAnswerIndex = correctAnswerIndex;
	}

	public int getParameter() {
		return parameter;
	}

	public void setParameter(int parameter) {
		this.parameter = parameter;
	}

	////////////////////////////////////////////////////////////////
	public Question[] getQuestions() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public int getAnswer() {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}

	public void setQuestions(List<Question> questions) {
		// TODO 自動生成されたメソッド・スタブ

	}
}
