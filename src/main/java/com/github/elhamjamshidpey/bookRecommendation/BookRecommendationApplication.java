package com.github.elhamjamshidpey.bookRecommendation;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.PostConstruct;

import org.h2.tools.Csv;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.github.elhamjamshidpey.bookRecommendation.data.Book;
import com.github.elhamjamshidpey.bookRecommendation.repository.BookRepository;

/*
@uthor by Elham
May 27, 2019
*/
@SpringBootApplication
public class BookRecommendationApplication {

    private static final Logger log = LoggerFactory.getLogger(BookRecommendationApplication.class);

	@Autowired
	private BookRepository bookRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(BookRecommendationApplication.class, args);
	}
	
	@PostConstruct
    private void init() {
        ResultSet rs;
        try {
        	log.info("Load books data from file");
        	rs = new Csv().read("src/main/resources/resources_books.csv", null, null);
        	while (rs.next()) {
        		Book book = new Book(Integer.valueOf(rs.getString(1)),
        				rs.getString(2),rs.getString(3),rs.getString(4));
        		bookRepository.save(book);
        	}
        	rs.close();

        } catch (SQLException e) {
        	log.error(e.getMessage());
        }
  
	}
	
}
