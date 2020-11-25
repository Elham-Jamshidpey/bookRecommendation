package com.github.elhamjamshidpey.bookRecommendation.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/*
@uthor by Elham
May 27, 2019
*/

@EnableWebSecurity
@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().httpBasic()
			.and().authorizeRequests().antMatchers(HttpMethod.GET,"/recommendations").authenticated()
        	.and().authorizeRequests().antMatchers(HttpMethod.POST,"/feedbacks").authenticated()
			.and().authorizeRequests().antMatchers(HttpMethod.POST,"/users").permitAll();
	}
}