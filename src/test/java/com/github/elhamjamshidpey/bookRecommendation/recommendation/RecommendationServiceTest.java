package com.github.elhamjamshidpey.bookRecommendation.recommendation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.elhamjamshidpey.bookRecommendation.data.Book;
import com.github.elhamjamshidpey.bookRecommendation.data.Feedback;
import com.github.elhamjamshidpey.bookRecommendation.data.LikeStatus;
import com.github.elhamjamshidpey.bookRecommendation.data.User;
import com.github.elhamjamshidpey.bookRecommendation.repository.BookRepository;
import com.github.elhamjamshidpey.bookRecommendation.service.FeedbackService;
import com.github.elhamjamshidpey.bookRecommendation.service.RecommendationService;
import com.github.elhamjamshidpey.bookRecommendation.service.UserService;

/*
@uthor by Elham
May 27, 2019
*/

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecommendationServiceTest {

	@MockBean
	private BookRepository bookRepository;

	@MockBean
	private FeedbackService feedbackService;
	
	@MockBean
	private UserService userService;
	
	@Autowired
	private RecommendationService recommendationService;
	
	@Test
	public void get_recommendations_for_user_without_feedback() {
		User user = new User("elham","123456");
		Mockito.when(userService.findByUsername("elham")).thenReturn(user);
		Optional<List<Feedback>> feedbacks = Optional.of(new ArrayList<Feedback>());
		Mockito.when(feedbackService.get(user)).thenReturn(feedbacks);
		Mockito.when(bookRepository.findAll()).thenReturn(mockBooks());
		List<Book> books = recommendationService.recommendBooksForUser(user.getUsername());
		Assert.assertTrue(books.size()==3);
	}
	
	@Test
	public void get_recommendations_for_user_with_feedback() {
		User user = new User("elham","123456");
		Mockito.when(userService.findByUsername("elham")).thenReturn(user);
		List<Feedback> feedbacks = new ArrayList<Feedback>();
		feedbacks.add(new Feedback(mockBooks().get(0),new User("elham","123456"),LikeStatus.LIKE));
		Mockito.when(feedbackService.get(user)).thenReturn(Optional.of(feedbacks));
		Mockito.when(bookRepository.findAll()).thenReturn(mockBooks());
		List<Book> books = recommendationService.recommendBooksForUser(user.getUsername());
		Assert.assertTrue(books.size()==2);
	}
	

	private List<Book> mockBooks() {
		List<Book> books = new ArrayList<Book>();
		Book b2 = new Book(2,"t2","a2","g2");
		books.add(b2);
		
		Book b1 = new Book(1,"t1","a1","g1");
		books.add(b1);

		Book b3 = new Book(3,"t3","a3","g3");
		books.add(b3);
		return books;
	}
}
