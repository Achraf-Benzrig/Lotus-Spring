package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.entity.ToDoList;

public interface IToDoListService {
	public void addToTheToDoList(ToDoList todo,String email);
	public void deleteFromTheToDoList(Long id);
	List<ToDoList> findByUserEmail(String email);
	public void updateToDoList(ToDoList todo,String email);

}
