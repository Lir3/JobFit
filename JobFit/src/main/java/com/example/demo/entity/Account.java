package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "account")
public class Account {

	//主キーには「@Id」を設定する！
	
    @Id
    @Column(name = "UserID")
    private int userID;

	@Column(name = "Age")
	private int Age;
	
    @Column(name = "username") 
    private String username;


    @Enumerated(EnumType.STRING)
    @Column(name = "Gender")
    private Gender gender;

    // Gender 列挙型（Enum）の定義
    public enum Gender {
        MALE,
        FEMALE
    }

    @Column(name = "Password")
    private String password;

    @Column(name = "Mailaddress")
    private String mailaddress;

    @Column(name = "type_history")
    private String type_history;

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getAge() {
		return Age;
	}

	public void setAge(int age) {
		Age = age;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMailaddress() {
		return mailaddress;
	}

	public void setMailaddress(String mailaddress) {
		this.mailaddress = mailaddress;
	}

	public String getType_history() {
		return type_history;
	}

	public void setType_history(String type_history) {
		this.type_history = type_history;
	}
    


	

    
    

}
