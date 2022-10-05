package tn.esprit.spring.User;

import lombok.AllArgsConstructor;
import tn.esprit.spring.entity.Blacklist;
import tn.esprit.spring.registration.EmailValidator;
import tn.esprit.spring.registration.token.ConfirmationToken;
import tn.esprit.spring.registration.token.ConfirmationTokenService;
import tn.esprit.spring.repository.BlacklistRepository;
import tn.esprit.spring.repository.SubscriberRepository;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG = "user with email %s not found";

    private final UserRepository appUserRepository;
    private final SubscriberRepository expertRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;
    private final BlacklistRepository blacklistRepository;

    
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = appUserRepository.findByEmail(email).orElse(null);
		
		List<Blacklist> blacklist = blacklistRepository.findAll();
		Boolean b = false;
		for (Blacklist blacklist2 : blacklist) {
			if(blacklist2.getEmail().equals(email)){
				b = true;
			}
			
		}
		if(user == null  || b == true){
			throw new UsernameNotFoundException("User not found in the data base");
		}
		if(user.getEnabled()==false||user.getLocked()==true) {
			throw new UsernameNotFoundException("You need To Confirm your email");
		
		}
		List<SimpleGrantedAuthority> authorities = getUserAuthority(user.getAppUserRole().name());
		return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(), authorities);
	}
	
	
	private List<SimpleGrantedAuthority> getUserAuthority(String userRoles) {
		Set<SimpleGrantedAuthority> roles = new HashSet<SimpleGrantedAuthority>();
		
		roles.add(new SimpleGrantedAuthority(userRoles)); 
		List<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<>(roles);
		return grantedAuthorities;
	}
	
   
	

    public String signUpUser(User appUser) {
        boolean userExists = appUserRepository
                .findByEmail(appUser.getEmail())
                .isPresent();

		List<Blacklist> blacklist = blacklistRepository.findAll();
		Boolean b = false;
		for (Blacklist blacklist2 : blacklist) {
			if(blacklist2.getEmail().equals(appUser.getEmail())){
				b = true;
			}
			
		}
		if(b == true){
			throw new IllegalStateException("blacklist");
		}
        if (userExists) {
        	User u = appUserRepository.findByEmail(appUser.getEmail()).orElse(null);
        	if(u.getEnabled()==false){
        		appUserRepository.save(u);
                String token = UUID.randomUUID().toString();

                ConfirmationToken confirmationToken = new ConfirmationToken(
                		token,
                		LocalDateTime.now(),
                		LocalDateTime.now().plusMinutes(15),
                		u
                		
                		);
                confirmationTokenService.saveConfirmationToken(confirmationToken);
                return "email already taken";
        	}else{
        		return "email already taken";
        	}
            
        }
    

        String encodedPassword = bCryptPasswordEncoder
                .encode(appUser.getPassword());

        appUser.setPassword(encodedPassword);

        appUserRepository.save(appUser);
        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(
        		token,
        		LocalDateTime.now(),
        		LocalDateTime.now().plusMinutes(15),
        		appUser
        		
        		);
        confirmationTokenService.saveConfirmationToken(confirmationToken);
        return "";
    }
    
    public int enableAppUser(String email) {
        return appUserRepository.enableAppUser(email);
    }
}
