package com.github.elhamjamshidpey.bookRecommendation.feedback;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.github.elhamjamshidpey.bookRecommendation.data.Book;
import com.github.elhamjamshidpey.bookRecommendation.data.User;
import com.github.elhamjamshidpey.bookRecommendation.repository.UserRepository;
import com.github.elhamjamshidpey.bookRecommendation.service.FeedbackService;
import com.github.elhamjamshidpey.bookRecommendation.service.RecommendationService;
import com.github.elhamjamshidpey.bookRecommendation.service.UserService;
import com.github.elhamjamshidpey.bookRecommendation.util.FeedbackMapperUtility;

/*
@uthor by Elham
May 27, 2019
*/

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class FeedbackApiControllerTest {
	
    @MockBean
    private FeedbackService feedbackService;
    
    @MockBean
    private UserService userService;
    
    @MockBean
    private UserRepository userRepository;
    
    @MockBean
    private FeedbackMapperUtility feedbackUtility;
    
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private RecommendationService recommendationService;
	
	
    @Test
    public void save_feedback_successfully() throws Exception{
    	User user = new User("elham","123456");
    	Mockito.when(userService.findByUsername("elham")).thenReturn(user);
    	Mockito.when(userRepository.findOne("elham")).thenReturn(user);
		this.mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON)
				.content("{\"username\":\"elham\",\"password\":\"123456\"}"));
    	this.mockMvc.perform(post("/feedbacks").contentType(MediaType.APPLICATION_JSON)
				.header("Authorization", "Basic ZWxoYW06MTIzNDU2")
				.header("accept", MediaType.APPLICATION_JSON)
				.content("[{\"ASIN\":1,\"like_status\":\"LIKE\"}]")).andDo(print())
				.andExpect(status().isOk());

	}
    
    @Test
    public void save_feedback_bad_request() throws Exception{
		User user = new User("elham","123456");
		Mockito.when(userService.findByUsername("elham")).thenReturn(user);
		this.mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON)
				.content("{\"username\":\"elham\",\"password\":\"123456\"}"));
		this.mockMvc.perform(post("/feedbacks").contentType(MediaType.APPLICATION_JSON)
				.header("Authorization", "Basic ZWxoYW06MTIzNDU2")
				.header("accept", MediaType.APPLICATION_JSON)
				.content("[{\"ASIN\":1,\"like_status\":\"INVA_LIKE\"}]")).andDo(print())
				.andExpect(status().isBadRequest());
	}


}
