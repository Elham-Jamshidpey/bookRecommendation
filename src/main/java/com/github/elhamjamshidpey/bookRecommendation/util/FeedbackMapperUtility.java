package com.github.elhamjamshidpey.bookRecommendation.util;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.elhamjamshidpey.bookRecommendation.data.Feedback;
import com.github.elhamjamshidpey.bookRecommendation.data.LikeStatus;
import com.github.elhamjamshidpey.bookRecommendation.data.User;
import com.github.elhamjamshidpey.bookRecommendation.dto.FeedbackDto;
import com.github.elhamjamshidpey.bookRecommendation.service.RecommendationService;

/*
@uthor by Elham
May 27, 2019
*/

@Component
public class FeedbackMapperUtility {

	@Autowired
	private RecommendationService recommendationService;
	
	public Function<FeedbackDto,Feedback> feddbackDtosTofeedback(User user){
    	return new Function<FeedbackDto,Feedback>(){
    		@Override
    		public Feedback apply(FeedbackDto feedbackDto) {
    			return new Feedback(recommendationService.findByASIN(feedbackDto.getASIN()),user,LikeStatus.valueOf(feedbackDto.getLikeStatus()));
    		}
    	};
	}
}
