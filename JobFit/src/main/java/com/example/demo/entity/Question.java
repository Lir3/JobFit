package com.example.demo.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "questions")
public class Question {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "text") 
    private String text;
    
    @ManyToOne
    @JoinColumn(name = "type_id")
    private Types type;
    
    @Column(name = "option_yes_score") 
    private int yes;
    
    @Column(name = "option_no_score") 
    private int no;
    
    @Column(name = "option_neutral_score") 
    private int neutral;
    
    @OneToMany(mappedBy = "question")
    private List<Answers> answers;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Types getType() {
		return type;
	}

	public void setType(Types type) {
		this.type = type;
	}

	public int getYes() {
		return yes;
	}

	public void setYes(int yes) {
		this.yes = yes;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getNeutral() {
		return neutral;
	}

	public void setNeutral(int neutral) {
		this.neutral = neutral;
	}

	public List<Answers> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answers> answers) {
		this.answers = answers;
	}

    
	
   
	
   
    

}
