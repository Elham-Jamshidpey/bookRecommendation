package com.github.elhamjamshidpey.bookRecommendation.api;


import java.security.Principal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.elhamjamshidpey.bookRecommendation.dto.BookDto;

/*
@uthor by Elham
May 27, 2019
*/

public interface RecommendationsApi {

    @RequestMapping(value = "/recommendations",method = RequestMethod.GET)
    ResponseEntity<List<BookDto>> recommendationsGet(Principal principal);

}