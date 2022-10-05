package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.User.User;
import tn.esprit.spring.User.UserRepository;
import tn.esprit.spring.entity.ToDoList;
import tn.esprit.spring.repository.ToDoListRepository;

@Service
public class ToDoListService implements IToDoListService{
	
	@Autowired
	ToDoListRepository myRepository;
	@Autowired
	UserRepository userRepository;

	@Override
	public void addToTheToDoList(ToDoList todo, String email) {
		User u = userRepository.findByEmail(email).orElse(null);
		todo.setUser(u);
		myRepository.save(todo);
	}
	
	@Override
	public void deleteFromTheToDoList(Long id) {
		myRepository.deleteById(id);
	}

	@Override
	public List<ToDoList> findByUserEmail(String email) {
		return myRepository.findByUserEmail(email);
	}

	@Override
	public void updateToDoList(ToDoList todo, String email) {
		User u = userRepository.findByEmail(email).orElse(null);
		todo.setUser(u);
		myRepository.save(todo);
		
	}

}
