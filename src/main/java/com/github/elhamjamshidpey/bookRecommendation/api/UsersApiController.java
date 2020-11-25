package com.github.elhamjamshidpey.bookRecommendation.api;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.elhamjamshidpey.bookRecommendation.data.User;
import com.github.elhamjamshidpey.bookRecommendation.dto.UserDto;
import com.github.elhamjamshidpey.bookRecommendation.service.UserService;

/*
@uthor by Elham
May 27, 2019
*/

@Controller
public class UsersApiController implements UsersApi {

    private static final Logger log = LoggerFactory.getLogger(UsersApiController.class);

    private final ObjectMapper objectMapper;
    
    private ModelMapper modelMapper = new ModelMapper();

    private final HttpServletRequest request;

    @Autowired
    private UserService userService;
    
    @Autowired
    public UsersApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> usersPost(@Valid @RequestBody UserDto user) {
        try {
			userService.register(modelMapper.map(user, User.class));
	        return new ResponseEntity<Void>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
    }

}
