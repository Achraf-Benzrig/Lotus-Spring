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
import tn.esprit.spring.entity.Question;
import tn.esprit.spring.service.IQuestionService;


@Api(tags = "Question")
@RestController
@RequestMapping("/Question")
public class QuestionController {
	
	@Autowired
	IQuestionService questionService;
	
	
	@PostMapping("/add-question/{text}/{answer}")
	@ResponseBody
	public void Add(@PathVariable String text,@PathVariable String answer) { 
		questionService.Add(text,answer);	
	}
	
	@GetMapping("/questions")
	@ResponseBody
	public List<Question> getAllquestions(){
		return questionService.getAllQuestions();
	}
	
	@DeleteMapping("/delete-question/{Idquestion}")
	@ResponseBody
	public void Deletequestion(@PathVariable Long Idquestion ) {
		questionService.delete(Idquestion );
	}
	
	@PutMapping("/update-question")
	@ResponseBody
	public void Update(@RequestBody Question q) 
	{
		questionService.update(q);	
	}
	

	@PutMapping("/Affecter/{IdQuiz}/{Idquestion}")
	@ResponseBody
	public void AffectQuestionToQuiz(@PathVariable("IdQuiz")Long IdQuiz,@PathVariable("Idquestion") Long Idquestion){
		questionService.AffectQuestionToQuiz(IdQuiz, Idquestion);
	}
	

}
