package com.github.elhamjamshidpey.bookRecommendation.user;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/*
@uthor by Elham
May 27, 2019
*/

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserApiControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void register_user_successfully() throws Exception{
		this.mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON)
				.content("{\"username\":\"elham.jamshidpey\",\"password\":\"12345678\"}"))
		.andDo(print()).andExpect(status().isCreated());
	}
	
	@Test
	public void duplicate_user() throws Exception{
		this.mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON)
				.content("{\"username\":\"elham\",\"password\":\"123456\"}"));
		this.mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON)
				.content("{\"username\":\"elham\",\"password\":\"1234\"}"))
		.andDo(print()).andExpect(status().isConflict());
	}
}