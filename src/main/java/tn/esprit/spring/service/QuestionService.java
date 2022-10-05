package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.User.UserRepository;
import tn.esprit.spring.entity.Question;
import tn.esprit.spring.entity.Quiz;
import tn.esprit.spring.repository.QuestionRepository;
import tn.esprit.spring.repository.QuizRepository;

@Service
public class QuestionService implements IQuestionService{

	@Autowired
	QuestionRepository questionRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	QuizRepository quizRepository;
	
	
	@Override
	public Question Add(String q,String answer) {
		Question question = new Question();
		question.setAnswers(answer);
		question.setText(q);
		
		return questionRepository.save(question);
	}

	@Override
	public Question update(Question question) {
		return questionRepository.save(question);
	}

	@Override
	public void delete(Long idQuestion) {
		questionRepository.deleteById(idQuestion);
		
	}

	@Override
	public List<Question> getAllQuestions() {
		return (List<Question>) questionRepository.findAll();

	}



	@Override
	public void AffectQuestionToQuiz(Long IdQuestion, Long IdQuiz) {
		Question q = questionRepository.findById(IdQuestion).orElse(null);
		Quiz quiz = quizRepository.findById(IdQuiz).orElse(null);
		//quiz.getQuestions().add(q);
		questionRepository.save(q);
		
	}
	

	//@Override
	//public List<Answer> findAnswersByQuestion(Long id) {
		// TODO Auto-generated method stub
	//	return null;
	//}

	//@Override
	//@Transactional(readOnly = true)
	//public List<Answer> findAnswersByQuestion(Long id) {
		//Question q = find(id);
	//	return q.getAnswers();
	//}

}
