package com.github.elhamjamshidpey.bookRecommendation.api;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.elhamjamshidpey.bookRecommendation.data.Book;
import com.github.elhamjamshidpey.bookRecommendation.dto.BookDto;
import com.github.elhamjamshidpey.bookRecommendation.service.RecommendationService;
import com.github.elhamjamshidpey.bookRecommendation.util.BookMappingUtility;

/*
@uthor by Elham
May 27, 2019
*/

@Controller
public class RecommendationsApiController implements RecommendationsApi {

    private static final Logger log = LoggerFactory.getLogger(RecommendationsApiController.class);

    @Autowired
    private RecommendationService recommendationService;
    
    @Autowired 
    private BookMappingUtility bookMappingUtility;
    
    private final ObjectMapper objectMapper;
    
    private ModelMapper modelMapper = new ModelMapper();
    
    private final HttpServletRequest request;

    @Autowired
    public RecommendationsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<List<BookDto>> recommendationsGet(Principal principal) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
        	List<Book> books =  recommendationService.recommendBooksForUser(principal.getName());
        	List<BookDto> bookDtos = books.stream().map(bookMappingUtility.booksToBookDtos()).collect(Collectors.<BookDto> toList());
            try {
                return new ResponseEntity<List<BookDto>>(bookDtos,HttpStatus.OK);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type application/json", e);
            }
        }
        return new ResponseEntity<List<BookDto>>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
