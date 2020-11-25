package com.github.elhamjamshidpey.bookRecommendation.api;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.elhamjamshidpey.bookRecommendation.data.Feedback;
import com.github.elhamjamshidpey.bookRecommendation.data.User;
import com.github.elhamjamshidpey.bookRecommendation.dto.FeedbackDto;
import com.github.elhamjamshidpey.bookRecommendation.service.FeedbackService;
import com.github.elhamjamshidpey.bookRecommendation.service.UserService;
import com.github.elhamjamshidpey.bookRecommendation.util.FeedbackMapperUtility;

/*
@uthor by Elham
May 27, 2019
*/

@Controller
public class FeedbacksApiController implements FeedbacksApi {

    private static final Logger log = LoggerFactory.getLogger(FeedbacksApiController.class);

    @Autowired
    private FeedbackService feedbackService;
    
    @Autowired
    private FeedbackMapperUtility feedbackUtility;
    
    @Autowired
    private UserService userService;
    
    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    public FeedbacksApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> feedbacksPost(Principal principal, @Valid @RequestBody List<FeedbackDto> feedbackDtos) {
        try {
        	User user = userService.findByUsername(principal.getName());
        	List<Feedback> feedbacks = feedbackDtos.stream()
        			.map(feedbackUtility.feddbackDtosTofeedback(user)).collect(Collectors.<Feedback> toList());
        	feedbackService.save(feedbacks);
        	return new ResponseEntity<Void>(HttpStatus.OK);
        }catch(Exception e) {
        	log.error(e.getMessage());
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);        	
        }

    }

}
