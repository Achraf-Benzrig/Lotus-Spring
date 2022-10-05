package tn.esprit.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import tn.esprit.spring.User.User;
import tn.esprit.spring.entity.ToDoList;
import tn.esprit.spring.service.IToDoListService;

@RestController
@Api(tags = "ToDoList management")
@RequestMapping("/todo")
@CrossOrigin(origins = "http://localhost:8089")
public class ToDoListController {
	
	@Autowired
	IToDoListService todoService;
	
	
	
	// http://localhost:8089/WomenEmpowerment/todo/findByUserEmail/
	@GetMapping("/findByUserEmail/{email}")
	@ResponseBody
	public List<ToDoList> findByUserEmail(@PathVariable("email") String email){
		return todoService.findByUserEmail(email);
	}
	
	
	//http://localhost:8089/WomenEmpowerment/todo/updateToDoList
	@PutMapping("/updateToDoList/{email}")
	@ResponseBody
	public void updateToDoList(@RequestBody ToDoList todo,@PathVariable("email") String email) {
		todoService.updateToDoList(todo,email);
	}
	
	
	// http://localhost:8089/WomenEmpowerment/todo/deleteFromTheToDoList/
	@DeleteMapping("/deleteFromTheToDoList/{id}")
	@ResponseBody
	public void deleteFromTheToDoList(@PathVariable("id") Long id){
		 todoService.deleteFromTheToDoList(id);
	}
	
	//http://localhost:8089/WomenEmpowerment/todo/addToTheToDoList
	@PostMapping("/addToTheToDoList/{email}")
	@ResponseBody
	public void addToTheToDoList(@RequestBody ToDoList todo,@PathVariable("email") String email) {
		todoService.addToTheToDoList(todo,email);
	}

}
