package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.Response.Com;
import tn.esprit.spring.User.User;
import tn.esprit.spring.User.UserRole;
import tn.esprit.spring.entity.Publication;

public interface IPublicationService {
	public Com AjouterPub(Publication pub ,String email) ;
	public List<Publication> getPublications();
	
	public Publication retrievePostById(Long idPost) ;
	public void deletePub(Long id);
	public void updatePub(Publication pub , String email);
	List<Publication> search(String keyword);
	public Publication getPubID(Long id);
	public List<Publication> suggpub(String  description,String  companyname,String  assoc,UserRole  role,int age);

}
