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
public boolean verifExistCalendrMedcin(RendezVous r);
public boolean verifExistCalendrLawyer(RendezVous r);
public boolean verifExistCalendrPsy(RendezVous r);
public boolean verifExistCalendr(RendezVous r);
}
