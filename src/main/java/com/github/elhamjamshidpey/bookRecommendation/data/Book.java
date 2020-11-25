package com.github.elhamjamshidpey.bookRecommendation.data;

import javax.persistence.Entity;
import javax.persistence.Id;

/*
@uthor by Elham
May 27, 2019
*/

@Entity
public class Book {

	@Id
	private Integer ASIN;
	private String title;
	private String author;
	private String genre;
	
	public Book(Integer ASIN,String title,String author,String genre) {
		this.ASIN = ASIN;
		this.title = title;
		this.author = author;
		this.genre = genre;
	}
	
	public Book() {
		
	}
	
	public Integer getASIN() {
		return ASIN;
	}
	public void setASIN(Integer ASIN) {
		this.ASIN = ASIN;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}

	@Override
	public String toString() {
		return "Book [ASIN=" + ASIN + ", title=" + title + ", author=" + author + ", genre=" + genre + "]";
	}

	@Override
	public int hashCode() {
		return ASIN.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return ASIN.equals(((Book)obj).ASIN);
	}
	
}
