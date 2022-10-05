package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.entity.Dislike;
import tn.esprit.spring.entity.Emojis;

public interface IEmojisService {
	public void AjouterEmo(Emojis emoj,Long idCo ,String email) ;
	public List<Emojis> getEmojis();
	public void deleteEmojis(Long id);

}
