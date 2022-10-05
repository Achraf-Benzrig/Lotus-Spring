package tn.esprit.spring.service;

import java.util.Date;
import java.util.List;

import tn.esprit.spring.entity.Cagnotte;

public interface ICagnotteService {

	//Cagnotte addcagnotte( Date dd, Date df);
	
	Cagnotte addcagnottetoevent(Long  idevent, Date dd, Date df);

	Cagnotte Updatecagnotte(Long idcag, float valeur, Date dd, Date df);

	Cagnotte getcag(Long idcag);

	List<Cagnotte> getallcag();

	String deletecagnotte(Long idcag);

	Cagnotte Findbyidevent(Long idevent);

	Cagnotte Findbyidcagnotte(Long idcag);

	Cagnotte addcagnotte(Cagnotte cag);

}
