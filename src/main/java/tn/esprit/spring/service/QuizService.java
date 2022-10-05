package tn.esprit.spring.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entity.Quiz;
import tn.esprit.spring.repository.QuizRepository;


@Service
public class QuizService implements IQuizService{
	
	@Autowired
	QuizRepository quizRepository;

	@Override
	public void AddQuiz(Quiz Q) { 
		quizRepository.save(Q);
}

	@Override
	public void UpdateQuiz(Quiz Q) {
		quizRepository.save(Q);
}

	@Override
	public void DeleteQuiz(Long idQuiz) {
		quizRepository.deleteById(idQuiz);
		
	}

	@Override
	public List<Quiz> getAllQuizs() {
		return (List<Quiz>) quizRepository.findAll();
	}	
	
//na9ssa ****** question et answer par quiz w postuler 
	

}
