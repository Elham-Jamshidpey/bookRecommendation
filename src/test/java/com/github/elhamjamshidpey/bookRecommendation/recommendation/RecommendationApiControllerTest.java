package com.github.elhamjamshidpey.bookRecommendation.recommendation;

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
import com.github.elhamjamshidpey.bookRecommendation.service.RecommendationService;

/*
@uthor by Elham
May 27, 2019
*/

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RecommendationApiControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private RecommendationService recommendationService;
	
	@Test
	public void get_recommendations_forUser() throws Exception{
		Mockito.when(recommendationService.recommendBooksForUser("elham")).thenReturn(mockBooks());
		this.mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON)
				.content("{\"username\":\"elham\",\"password\":\"123456\"}"));
		this.mockMvc.perform(get("/recommendations").contentType(MediaType.APPLICATION_JSON)
				.header("Authorization", "Basic ZWxoYW06MTIzNDU2")
				.header("accept", MediaType.APPLICATION_JSON)
				.content("")).andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("asin")))
				.andExpect(content().string(containsString("title")))
				.andExpect(content().string(containsString("author")))
				.andExpect(content().string(containsString("genre")));
	}

	@Test
	public void user_not_valid() throws Exception{
		Mockito.when(recommendationService.recommendBooksForUser("elham")).thenReturn(mockBooks());
		this.mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON)
				.content("{\"username\":\"elham\",\"password\":\"123456\"}"));
		this.mockMvc.perform(get("/recommendations").contentType(MediaType.APPLICATION_JSON)
				.header("Authorization", "invalid credentioal")
				.header("accept", MediaType.APPLICATION_JSON)
				.content("")).andDo(print())
				.andExpect(status().isUnauthorized());
	}
	
	private List<Book> mockBooks() {
		List<Book> books = new ArrayList<Book>();
		Book b1 = new Book(1,"b1","t1","g1");
		books.add(b1);
		Book b2 = new Book(2,"b2","t2","g2");
		books.add(b2);
		Book b3 = new Book(3,"b3","t3","g3");
		books.add(b3);
		return books;
	}
}
