package com.github.elhamjamshidpey.bookRecommendation.configuration;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.github.elhamjamshidpey.bookRecommendation.repository.UserRepository;

/*
@uthor by Elham
May 27, 2019
*/
@Configuration
public class AuthenticationConfiguration extends GlobalAuthenticationConfigurerAdapter{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void init(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService()).passwordEncoder(new BCryptPasswordEncoder());
	}

	UserDetailsService userDetailsService() {
		return new UserDetailsService() {

			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				com.github.elhamjamshidpey.bookRecommendation.data.User user = userRepository.findOne(username);
				List<GrantedAuthority> createAuthorityList = AuthorityUtils.createAuthorityList("USER");
				return new User(username, user.getPassword(), createAuthorityList);
			}
		};
	}
}
