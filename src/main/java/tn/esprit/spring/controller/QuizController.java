package tn.esprit.spring.controller;


	
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import tn.esprit.spring.entity.Quiz;
import tn.esprit.spring.service.IQuizService;

@Api(tags = "Quiz management")
@RestController
@RequestMapping("/Quiz")
public class QuizController {
	
	@Autowired
	IQuizService quizService;
	
	@PostMapping("/add-quiz")
	@ResponseBody
	public void AddQuiz(@RequestBody Quiz Q) {
		quizService.AddQuiz(Q);	
	}
	
	@PutMapping("/update-quiz/{IdQuiz}")
	@ResponseBody
	public void UpdateQuiz(@RequestBody Quiz Q) {
		quizService.UpdateQuiz(Q);	
	}
	
	@GetMapping("/quizs")
	@ResponseBody
	public List<Quiz> getAllQuizs(){
		return quizService.getAllQuizs();
	}
	
	@DeleteMapping("/delete-quiz/{IdQuiz}")
	//@RequestMapping(value = "/delete-quiz", method = RequestMethod.DELETE)
	@ResponseBody
	public void DeleteQuiz(@PathVariable Long IdQuiz ) {
		quizService.DeleteQuiz(IdQuiz );
	}

}
