package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.entity.Reclamation;

 public interface IReclamationService {
 public void addReclamation(Reclamation r,Long id);
 public List<Reclamation> getAllReclamations();
 public void deleteReclamation(Long id);
 public void marqueTraitee(Long id);
 public List<Reclamation> getReclamationsByUser(Long idUser);
 public List<Reclamation> getReclamationsNonTraitees();
 public List<Reclamation> getReclamationsTraitees();
 }
