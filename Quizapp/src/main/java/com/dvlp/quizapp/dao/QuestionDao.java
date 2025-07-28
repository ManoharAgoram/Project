package com.dvlp.quizapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dvlp.quizapp.model.Question;

public interface QuestionDao extends JpaRepository<Question, Integer> {

	List<Question> findByCategory(String Category);

	@Query(value="SELECT * FROM question q where q.category=:category ORDER BY RAND()",nativeQuery = true)
	List<Question> findRandomQuestionsByCategory(String category, int numQ);
	

}
