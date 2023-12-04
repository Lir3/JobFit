package com.example.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.entity.Question;

public class QuestionDAO {
	private static final String SELECT_ALL_QUESTIONS = "SELECT QuestionID, text, choice1, choice2, choice3, correct_answer FROM 問題";

	public List<Question> getAllQuestions() {
		List<Question> questions = new ArrayList<>();

		try (Connection connection = DatabaseConnector.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_QUESTIONS);
				ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				int id = resultSet.getInt("QuestionID");
				String text = resultSet.getString("text");
				String choice1 = resultSet.getString("choice1");
				String choice2 = resultSet.getString("choice2");
				String choice3 = resultSet.getString("choice3");
				int correctAnswerIndex = resultSet.getInt("correct_answer");

				String[] choices = { choice1, choice2, choice3 };

				Question question = new Question(id, text, choices, correctAnswerIndex);
				question.setQuestionID(id);

				questions.add(question);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return questions;
	}
}
