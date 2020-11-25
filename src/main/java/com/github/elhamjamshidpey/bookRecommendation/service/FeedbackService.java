package com.github.elhamjamshidpey.bookRecommendation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.elhamjamshidpey.bookRecommendation.data.Feedback;
import com.github.elhamjamshidpey.bookRecommendation.data.User;
import com.github.elhamjamshidpey.bookRecommendation.repository.FeedbackRepository;

/*
@uthor by Elham
May 27, 2019
*/

@Service
public class FeedbackService {
	
	@Autowired 
	private FeedbackRepository feedbackRepository;
	
	public void save(List<Feedback> feedbacks) {
		//feedbacks are updatable
		feedbackRepository.save(feedbacks);
	}
	
	public Optional<List<Feedback>> get(User user){
		Optional<List<Feedback>> userFeedbacks = feedbackRepository.findByUser(user);
		return userFeedbacks;
	}
	
}
