package com.github.elhamjamshidpey.bookRecommendation.api;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.elhamjamshidpey.bookRecommendation.dto.UserDto;

/*
@uthor by Elham
May 27, 2019
*/

public interface UsersApi {

    @RequestMapping(value = "/users",produces = { "application/json" }, 
    		consumes = { "application/json" },method = RequestMethod.POST)
    ResponseEntity<Void> usersPost(@Valid @RequestBody UserDto user);

}
