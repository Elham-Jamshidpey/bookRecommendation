package com.github.elhamjamshidpey.bookRecommendation.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.github.elhamjamshidpey.bookRecommendation.data.User;
import com.github.elhamjamshidpey.bookRecommendation.repository.UserRepository;

/*
@uthor by Elham
May 27, 2019
*/

@Service
public class UserService {
	
    private static final Logger log = LoggerFactory.getLogger(UserService.class);

	private final static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private UserRepository userRepository;
	
	public void register(User user) throws Exception{
		if(userRepository.findOne(user.getUsername())!=null) {
			log.error("Duplicate user");
			throw new Exception("Duplicate user");
		}
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userRepository.save(user);
	}
	
	public User findByUsername(String username) {
		return userRepository.findOne(username);
	}
}