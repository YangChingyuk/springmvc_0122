package com.yqx.entity;

import java.sql.Timestamp;
import java.util.Date;

public class Student {

	private int id;
	private String name;
	private String username;
	private String password;
	private int sex;
	private int age;
	private Date birthday;
	private Timestamp creatTime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Timestamp getCreatTime() {
		return creatTime;
	}
	public void setCreatTime(Timestamp creatTime) {
		this.creatTime = creatTime;
	}
	public Student(int id, String name, String username, String password, int sex, int age, Date birthday,
			Timestamp creatTime) {
		super();
		this.id = id;
		this.name = name;
		this.username = username;
		this.password = password;
		this.sex = sex;
		this.age = age;
		this.birthday = birthday;
		this.creatTime = creatTime;
	}
	public Student() {
		super();
	}
}
