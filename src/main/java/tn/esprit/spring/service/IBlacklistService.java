package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.entity.Blacklist;

public interface IBlacklistService {
	public void addUserToBlacklist(String email);
	public void deleteUserFromBlacklist(Long id);
	public List<Blacklist> getAllBlacklist();

}
