package tn.esprit.spring.User;

import java.net.URI;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.annotations.Api;
import lombok.Data;
import tn.esprit.spring.entity.SuspiciousAccount;

@RestController
@Api(tags = "User management")
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:8089")
public class UserController {
	
	@Autowired
	IUserManagement userService;
	
	// http://localhost:8089/WomenEmpowerment/user/findByEmail
	@GetMapping("/findByEmail/{email}")
	@ResponseBody
	public User findByEmail(@PathVariable("email") String email){
		return userService.findByEmail(email);
	}
	
	// http://localhost:8089/WomenEmpowerment/user/getUsers
	@GetMapping("/getUsers")
	@ResponseBody
	public ResponseEntity<List<User>> getUsers(){
		return ResponseEntity.ok().body(userService.getUsers());
		
	}
	
	// http://localhost:8089/WomenEmpowerment/user/deleteUser/
	@DeleteMapping("/deleteUser/{email}")
	@ResponseBody
	public void deleteUser(@PathVariable("email") String email){
		userService.deleteUser(email);
		
	}
	
	
	//http://localhost:8089/WomenEmpowerment/user/updateUser
	@PutMapping("/updateUser")
	@ResponseBody
	public void updateUser(@RequestBody User user) {
	userService.updateUser(user);
	}
	
	
	// http://localhost:8089/WomenEmpowerment/user/findByFirstNameContains/
	@GetMapping("/findByFirstNameContains/{firstName}")
	@ResponseBody
	public List<User> findByFirstNameContains(@PathVariable("firstName") String firstName){
		return userService.findByFirstNameContains(firstName);
	}
	
	// http://localhost:8089/WomenEmpowerment/user/findByFirstNameOrLastNameContains/
	@GetMapping("/findByFirstNameOrLastNameContains/{firstName}/{lastName}")
	@ResponseBody
	public List<User> findByFirstNameOrLastNameContains(@PathVariable("firstName") String firstName,@PathVariable("lastName") String lastName){
		return userService.findByFirstNameOrLastNameContains(firstName,lastName);
	}
	
	
	// http://localhost:8089/WomenEmpowerment/user/findByLocked/
	@GetMapping("/findByLocked/{locked}")
	@ResponseBody
	public List<User> findByLocked(@PathVariable("locked") Boolean locked){
		return userService.findByLocked(locked);
	}
	
	// http://localhost:8089/WomenEmpowerment/user/unlockedAppUser/
	@PutMapping("/unlockedAppUser/{email}")
	@ResponseBody
	public int unlockedAppUser(@PathVariable("email") String email){
		return userService.unlockedAppUser(email);
	}
	
	// http://localhost:8089/WomenEmpowerment/user/lockedAppUser/
	@PutMapping("/lockedAppUser/{email}")
	@ResponseBody
	public int lockedAppUser(@PathVariable("email") String email){
		return userService.lockedAppUser(email);
	}
	
	
	// http://localhost:8089/WomenEmpowerment/user/countTotalUsersByYear
	@GetMapping("/countTotalUsersByYear")
	@ResponseBody
	public List<Object[]> countTotalUsersByYear(){
		return userService.countTotalUsersByYear();
	}
	
	
	
	
	// http://localhost:8089/WomenEmpowerment/user/UserPrediction
	@GetMapping("/UserPrediction/{email}")
	@ResponseBody
	public Map<String, Float> UserPrediction(@PathVariable("email") String email){
		return userService.UserPrediction(email);
	}
	
	
	// http://localhost:8089/WomenEmpowerment/user/fakeAccounts
    @RequestMapping(value = "/fakeAccounts", method = RequestMethod.POST, produces = MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] fakeAccounts(@RequestParam("file") MultipartFile file){
    	return userService.fakeAccounts(file).toImage();
	}
    
    
    
	// http://localhost:8089/WomenEmpowerment/user/getFakeAccounts
	@GetMapping("/getFakeAccounts")
	@ResponseBody
	public List<SuspiciousAccount> getFakeAccounts(){
		return userService.getFakeAccounts();
		
	}
	
	
	// http://localhost:8089/WomenEmpowerment/user/deleteFakeAccounts/
	@DeleteMapping("/deleteFakeAccounts/{id}")
	@ResponseBody
	public void deleteFakeAccounts(@PathVariable("id") Long id){
		userService.deleteFakeAccounts(id);
		
	}

	


}
