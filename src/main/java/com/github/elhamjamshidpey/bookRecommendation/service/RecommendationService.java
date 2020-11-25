package com.github.elhamjamshidpey.bookRecommendation.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.elhamjamshidpey.bookRecommendation.data.Book;
import com.github.elhamjamshidpey.bookRecommendation.data.Feedback;
import com.github.elhamjamshidpey.bookRecommendation.data.LikeStatus;
import com.github.elhamjamshidpey.bookRecommendation.data.User;
import com.github.elhamjamshidpey.bookRecommendation.repository.BookRepository;

/*
@uthor by Elham
May 27, 2019
*/

@Service
public class RecommendationService {

	private static final Integer RESULT_SIZE = 20;
	
	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private FeedbackService feedbackService;
	
	@Autowired
	private UserService userService;
	
	public List<Book> findAll() {
		return bookRepository.findAll();
	}

	public Book findByASIN(Integer ASIN) {
		return bookRepository.findOne(ASIN);
	}

	public List<Book> recommendBooksForUser(String username) {

		Map<String, Integer> genresWeight = new HashMap<String, Integer>();
		Map<String, Integer> authorsWeight = new HashMap<String, Integer>();
		Map<Book, Integer> booksWeights = new HashMap<Book, Integer>();
		List<Book> recommendedBooks = new ArrayList<Book>();
		
		User currentUser = userService.findByUsername(username);
		Optional<List<Feedback>> userFeedBacks = feedbackService.get(currentUser);
		List<Book> books = bookRepository.findAll();

		
		if(userFeedBacks.isPresent()) {
			//initialize genres and authors weight
			books.forEach(book ->{
				genresWeight.put(book.getGenre(), 0);
				authorsWeight.put(book.getAuthor(), 0);
			});
		
			userFeedBacks.ifPresent(feedbacks -> feedbacks.forEach(feedback ->{
				//fill genre-weight and author-weight map based on user feedback
				genresWeight.put(feedback.getBook().getGenre(), genresWeight.get(feedback.getBook().getGenre())
					+feedback.getLikeStatus().getValue());
				authorsWeight.put(feedback.getBook().getAuthor(), authorsWeight.get(feedback.getBook().getAuthor())
					+feedback.getLikeStatus().getValue());
			
			}));
		
			//remove books which have user feedback
			userFeedBacks.ifPresent(f -> f.forEach(feedBack -> {
				books.remove(feedBack.getBook());
			}));

			//Generate recommendation books
			books.forEach(book -> {
				Integer bookGenreWeight = genresWeight.get(book.getGenre());
				Integer bookAuthorWeight = authorsWeight.get(book.getAuthor());
				Integer bookWeight = bookGenreWeight + bookAuthorWeight;
				booksWeights.put(book, bookWeight);
			});
			booksWeights.entrySet().stream().sorted(Map.Entry.<Book, Integer>comparingByValue()).limit(RESULT_SIZE)
					.forEach(item -> recommendedBooks.add((Book) item.getKey()));
			
			}else {
				books.stream().limit(RESULT_SIZE).forEach(b -> recommendedBooks.add(b));
			}

			return recommendedBooks;
		}

}

