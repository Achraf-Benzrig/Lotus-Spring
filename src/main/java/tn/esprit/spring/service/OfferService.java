package tn.esprit.spring.service;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.User.User;
import tn.esprit.spring.User.UserRepository;
import tn.esprit.spring.entity.Offer;
import tn.esprit.spring.repository.OfferRepository;

@Service
public class OfferService implements IOfferService{

	

	@Autowired
	OfferRepository offerRepository;
	
	@Autowired
	UserRepository userRepository;

	@Override
	public void AddOffer(Offer f) {
		
		offerRepository.save(f);
		}
	
	@Override
	public  List<Object[]> statistic () {

	List<Object[]> s = offerRepository.countDestinationByDomainUser();


	return s;
	}

	@Override
	public List<Offer> getAllOffers() {
		return (List<Offer>) offerRepository.findAll();
	}
	

	@Override
	public void UpdateOffer(Offer f) {
		offerRepository.save(f);
		}

	@Override
	public void DeleteOffer(Long idOffer) {
		offerRepository.deleteById(idOffer);
		}

	@Override
	public List<Offer> FindOfferByTitle(String Title) {
		
		 return offerRepository.FindOfferByTitle(Title);
	}

	//Filter???????????
	@Override
	public List<Offer> search(String keyword){
        if (keyword != null) {
            return offerRepository.search(keyword);
        }
        return (List<Offer>) offerRepository.findAll();
    }

	@Override
	public void JobApplication(Long IdOffer, String email) {
		Offer o = offerRepository.getById(IdOffer);
		User u = userRepository.findByEmail(email).orElse(null);
		Set<User> lu = o.getUser();
		lu.add(u);
		o.setUser(lu);
		offerRepository.save(o);
		
	}

	@Override 
	public void AffecterOfferByUserId(Long IdOffer, String email) { //publier les offer par Identreprise
		User u = userRepository.findByEmail(email).orElse(null);
		Offer o = offerRepository.findById(IdOffer).orElse(null);
		o.getUser().add(u);
		offerRepository.save(o);	
	}
	
	public List<Offer> suggestedOffer(Long idUser){
		User u = userRepository.findById(idUser).orElse(null);
		return offerRepository.suggestedOffer(u.getProfession() , u.getNiveauetude() , u.getDescription() , u.getAdress() );

	}
}
	
	
	
	
	
	
	
	
	
	
	
	
	//pagination
	//@Override
	//public List<Offer> findAll(org.springframework.data.domain.Pageable pageable) {
		//Sort sort = new Sort(direction, ordering);
	//	PageRequest page = new PageRequest(xoffset, xbase, sort);

	//	return (List<Offer>) offerRepository.findAll(page);
	//}
	
	//@Override
	//public void UpdateOfferById(Long idOffer) {
		//offerRepository.saveAll(idOffer);
		
//	}
	



