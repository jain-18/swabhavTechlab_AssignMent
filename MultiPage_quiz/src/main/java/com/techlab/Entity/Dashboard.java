package com.techlab.Entity;

import java.sql.Time;
import java.sql.Timestamp;

public class Dashboard {
	private int id;
	private int userId;
	private int marks;
	private int totalMarks;
	private Timestamp testTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getMarks() {
		return marks;
	}

	public void setMarks(int marks) {
		this.marks = marks;
	}

	public Timestamp getTestTime() {
		return testTime;
	}

	public void setTestTime(Timestamp testTime) {
		this.testTime = testTime;
	}

	public int getTotalMarks() {
		return totalMarks;
	}

	public void setTotalMarks(int totalMarks) {
		this.totalMarks = totalMarks;
	}

}
