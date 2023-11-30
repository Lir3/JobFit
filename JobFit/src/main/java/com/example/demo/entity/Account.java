package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
//↓「name="xxx"」の「xxx」の部分に模倣したいテーブル名を書く
@Table(name = "Account")
public class Account {

	
	//主キーには「@Id」を設定する！
	@Id
	//カラム名(列名)を書く。
	@Column(name = "UserID")
	private int UserID;

	@Column(name = "UserName")
	private String userName;
	
	@Column(name = "Age")
	private int Age;

	@Column(name = "Gender")
	private String Gender;
	
	@Column(name = "Password")
	private String password;
	
	@Column(name = "Mailaddress")
	private String Mailaddress;
	

	public int getUserID() {
		return UserID;
	}

	public void setUserID(int userID) {
		UserID = userID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getAge() {
		return Age;
	}

	public void setAge(int age) {
		Age = age;
	}

	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		Gender = gender;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMailaddress() {
		return Mailaddress;
	}

	public void setMailaddress(String mailaddress) {
		Mailaddress = mailaddress;
	}
	
	

}