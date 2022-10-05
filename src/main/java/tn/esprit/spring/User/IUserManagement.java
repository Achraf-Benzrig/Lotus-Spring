package tn.esprit.spring.User;

import java.util.List;


public interface IUserManagement {
	public List<User> getUsers();
	public void deleteUser(String email);
	public void updateUser(User user);

}
