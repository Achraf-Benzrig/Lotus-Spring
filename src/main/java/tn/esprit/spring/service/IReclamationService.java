package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.entity.Reclamation;

 public interface IReclamationService {
 public void addReclamation(Reclamation r);
 public List<Reclamation> getAllReclamations();
 public void deleteReclamation(Long id);
 //public void updateReclamation(String Code);
}
