package com.github.elhamjamshidpey.bookRecommendation.data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/*
@uthor by Elham
May 27, 2019
*/

@Entity
@IdClass(FeedbackId.class)
public class Feedback {
	

	@Id
	@ManyToOne
	@NotNull
	private Book book;

	@Id
	@ManyToOne
	@NotNull
	private User user;
	
	@NotNull
	private LikeStatus likeStatus;
	
	public Feedback() {
	}
	
	public Feedback(Book book, User user, LikeStatus likeStatus) {
		this.book = book;
		this.user = user;
		this.likeStatus = likeStatus;
	}
	
	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LikeStatus getLikeStatus() {
		return likeStatus;
	}
	public void setLikeStatus(LikeStatus likeStatus) {
		this.likeStatus = likeStatus;
	}

	@Override
	public int hashCode() {
		return user.getUsername().hashCode()+book.getASIN().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return user.getUsername().equals(((Feedback)obj).getUser().getUsername()) &&
				book.getASIN().equals(((Feedback)obj).getBook().getASIN());
	}
	
	
}