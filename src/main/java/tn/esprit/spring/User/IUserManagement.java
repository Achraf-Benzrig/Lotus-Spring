package tn.esprit.spring.User;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import tn.esprit.spring.entity.SuspiciousAccount;


public interface IUserManagement {
	public List<User> getUsers();
	public User findByEmail(String email);
	public void deleteUser(String email);
	public void updateUser(User user);
	List<User> findByFirstNameContains(String firstName);
	List<User> findByFirstNameOrLastNameContains(String firstName,String lastName);
	List<User> findByLocked(Boolean locked);
    int unlockedAppUser(String email);
    int lockedAppUser(String email);
    List<Object[]> countTotalUsersByYear();
    Map<String, Float> UserPrediction(String email);
    public UserManagement fakeAccounts(MultipartFile file);
    public List<SuspiciousAccount> getFakeAccounts();
    public void deleteFakeAccounts(Long id);

}
