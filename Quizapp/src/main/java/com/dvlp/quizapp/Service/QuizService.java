package com.dvlp.quizapp.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dvlp.quizapp.dao.QuestionDao;
import com.dvlp.quizapp.dao.QuizDao;
import com.dvlp.quizapp.model.Question;
import com.dvlp.quizapp.model.QuestionWrapper;
import com.dvlp.quizapp.model.Quiz;
import com.dvlp.quizapp.model.Responses;

@Service
public class QuizService {
	@Autowired
	QuizDao quizDao;

	@Autowired
	QuestionDao questionDao;

	public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

		List<Question> questions = questionDao.findRandomQuestionsByCategory(category, numQ);

		Quiz quiz = new Quiz();
		quiz.setTitle(title); 
		quiz.setQuestions(questions);
		quizDao.save(quiz);
		return new ResponseEntity<>("Success", HttpStatus.CREATED);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(int id) {

		Optional<Quiz> quiz = quizDao.findById(id);
		List<Question> questionsFromDB = quiz.get().getQuestions();
		List<QuestionWrapper> questionForUser = new ArrayList<>();
		for (Question q : questionsFromDB) {
			QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(),
					q.getOption3(), q.getOption4());
			questionForUser.add(qw);
		}  
		return new ResponseEntity<>(questionForUser, HttpStatus.OK);
	}

	public ResponseEntity<Integer> calculateResult(Integer id, List<Responses> responses) {

		Quiz quiz = quizDao.findById(id).get();
		List<Question> questions = quiz.getQuestions();
		int right = 0;

		for (Responses response : responses) {
			for (Question question : questions) {
				if (question.getId().equals(response.getId())
						&& question.getRightAnswer().equalsIgnoreCase(response.getResponse())) {
					right++;
					break;
				}
			}
		}
		return new ResponseEntity<>(right, HttpStatus.OK);
	}
	

}
