package com.dvlp.quizapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dvlp.quizapp.model.Quiz;


public interface QuizDao extends JpaRepository<Quiz,Integer>{
	
}
