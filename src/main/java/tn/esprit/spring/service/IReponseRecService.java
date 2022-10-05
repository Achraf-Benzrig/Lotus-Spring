package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.entity.ReponseRec;

public interface IReponseRecService {
public String addReponse(ReponseRec reponse,Long id);
public long getTempsAttenteReclamation(Long idReclamation);
public long getTempsAttenteMoyenReclamation();
public List<String> suggestion(Long id);
}
