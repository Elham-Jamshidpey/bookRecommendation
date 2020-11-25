package com.github.elhamjamshidpey.bookRecommendation.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.elhamjamshidpey.bookRecommendation.data.Feedback;
import com.github.elhamjamshidpey.bookRecommendation.data.User;

/*
@uthor by Elham
May 27, 2019
*/

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback,Long>{

	Optional<List<Feedback>> findByUser(User user);
}
