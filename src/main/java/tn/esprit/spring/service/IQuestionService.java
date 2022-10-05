package tn.esprit.spring.service;

import java.util.List;
import tn.esprit.spring.entity.Question;

public interface IQuestionService {
	
	public Question Add(String question,String answer);
	//Answer find(Long id);
	Question update(Question question);
	void delete(Long idQuestion);
	public List<Question> getAllQuestions();
	//List<Answer> findAnswersByQuestion(Long id);
	public void AffectQuestionToQuiz(Long IdQuestion, Long IdQuiz);

}
