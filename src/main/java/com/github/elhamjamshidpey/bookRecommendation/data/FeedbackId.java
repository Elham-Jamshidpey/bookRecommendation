package com.github.elhamjamshidpey.bookRecommendation.data;

import java.io.Serializable;

/*
@uthor by Elham
May 27, 2019
*/


public class FeedbackId implements Serializable{

	private String user;
	
	private Integer book;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Integer getBook() {
		return book;
	}

	public void setBook(Integer book) {
		this.book = book;
	}
	
	
}

