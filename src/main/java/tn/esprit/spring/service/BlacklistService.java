package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.User.User;
import tn.esprit.spring.User.UserRepository;
import tn.esprit.spring.entity.Blacklist;
import tn.esprit.spring.repository.BlacklistRepository;

@Service
public class BlacklistService implements IBlacklistService{

	@Autowired
	BlacklistRepository blacklistRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public void addUserToBlacklist(String email) {
		
		Blacklist b = new Blacklist();
		b.setEmail(email);
		blacklistRepository.save(b);
		
	}

	@Override
	public void deleteUserFromBlacklist(Long id) {
		blacklistRepository.deleteById(id);
		
	}

	@Override
	public List<Blacklist> getAllBlacklist() {
		return blacklistRepository.findAll();
	}

}
