package com.github.elhamjamshidpey.bookRecommendation.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.elhamjamshidpey.bookRecommendation.data.Book;

/*
@uthor by Elham
May 27, 2019
*/

@Repository
public interface BookRepository extends JpaRepository<Book,Integer>{

}
