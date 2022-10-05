package tn.esprit.spring.service;

import java.util.Date;

import tn.esprit.spring.entity.Cagnotte;
import tn.esprit.spring.entity.Don;

public interface IDonService {


	Don addDon(Long idcag, float montant, Date datedon, String email);

}
