package com.github.elhamjamshidpey.bookRecommendation.api;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.elhamjamshidpey.bookRecommendation.dto.FeedbackDto;

/*
@uthor by Elham
May 27, 2019
*/

public interface FeedbacksApi {

    @RequestMapping(value = "/feedbacks",method = RequestMethod.POST)
    ResponseEntity<Void> feedbacksPost(Principal principal, @Valid @RequestBody List<FeedbackDto> feedbackDtos);

}
