package tn.esprit.spring.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;

import tn.esprit.spring.User.User;
import tn.esprit.spring.User.UserRepository;
import tn.esprit.spring.entity.Cagnotte;
import tn.esprit.spring.entity.Don;
import tn.esprit.spring.entity.Evenement;
import tn.esprit.spring.repository.CagnotteRepository;
import tn.esprit.spring.repository.DonRepository;
import tn.esprit.spring.repository.EvenementRepository;

@Service
public class DonService implements IDonService {

	@Autowired
	CagnotteRepository cagRepository;
	
	@Autowired
	EvenementRepository eventRepository;
	
	@Autowired 
	DonRepository donRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private JavaMailSender mailsender;
	
	@Override
    public Don addDon(Long  idcag, float montant,Date datedon, String email){
		
		User user = userRepository.findByEmail(email).orElse(null);
		Don don = new Don();
		don.setDateDon(datedon);
		don.setMontant(montant);
		String str2 = Float.toString(montant);
		don.setUser(user);
		Cagnotte e =  cagRepository.findById(idcag).orElse(null);
		don.setCagnotte(e);
		
        SimpleMailMessage message = new SimpleMailMessage();
		
		message.setFrom("faroukgasaraa@gmail.com");
		message.setTo("mohamedali.omri@esprit.tn");
		message.setSubject("Nouvelle don");
		message.setText("Une montant de : " + str2 + " ete ajouter dapres Mr. " + user.getFirstName() + " " + user.getLastName());
		System.out.println("success");
		
		mailsender.send(message);
		
		donRepository.save(don);
		return don;
		
	}
}
