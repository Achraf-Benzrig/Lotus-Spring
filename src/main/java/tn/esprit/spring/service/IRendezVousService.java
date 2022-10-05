package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.entity.Calendrier;
import tn.esprit.spring.entity.RendezVous;

public interface IRendezVousService {
public String addRendezVous(RendezVous r, Long userId);
public List<RendezVous> getAllRendezVous();
public String updateRendezVous(RendezVous r,Long userId);
public String deleteRendezVous(Long id);
public boolean verifRendezVousDateInput(RendezVous r);
public int[] verifExistCalendrMedcin(RendezVous r);
public int[] verifExistCalendrLawyer(RendezVous r);
public int[] verifExistCalendrPsy(RendezVous r);
public int[] verifExistCalendr(RendezVous r);
public String generateCode(); 
public String confirm(String code);
String buildEmailredv(String name, String code, String link);
String buildEmailMeet(String name, String code, String link);
public void sendMeetLink();
}
