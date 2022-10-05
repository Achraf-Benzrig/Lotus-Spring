package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.entity.Quiz;

public interface IQuizService {

	public void AddQuiz(Quiz Q);
	public void UpdateQuiz(Quiz Q);

	public void DeleteQuiz(Long idQuiz);
	public List<Quiz> getAllQuizs();

}
