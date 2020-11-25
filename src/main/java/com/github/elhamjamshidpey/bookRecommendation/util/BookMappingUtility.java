package com.github.elhamjamshidpey.bookRecommendation.util;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.github.elhamjamshidpey.bookRecommendation.data.Book;
import com.github.elhamjamshidpey.bookRecommendation.dto.BookDto;

/*
@uthor by Elham
May 27, 2019
*/

@Component
public class BookMappingUtility {
	
	public Function<Book,BookDto> booksToBookDtos(){
    	return new Function<Book,BookDto>(){
    		@Override
    		public BookDto apply(Book book) {
    			return new BookDto(book.getASIN(),book.getTitle(),book.getAuthor(),book.getGenre());
    		}
    	};
	}

}
