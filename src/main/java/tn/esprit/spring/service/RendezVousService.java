package tn.esprit.spring.service;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.util.EnumUtils;

import tn.esprit.spring.User.User;
import tn.esprit.spring.User.UserRepository;
import tn.esprit.spring.entity.Calendrier;
import tn.esprit.spring.entity.RendezVous;
import tn.esprit.spring.registration.email.EmailSender;
import tn.esprit.spring.repository.CalendrierRepository;
import tn.esprit.spring.repository.RendezVousRepsitory;

@Service
public class RendezVousService implements IRendezVousService{

	@Autowired
	RendezVousRepsitory rendezVousRepo;
	@Autowired
	UserRepository userRepo;
	@Autowired
	CalendrierRepository calendrRepo;
	@Autowired
	ICalendrierServices calendrService;
	@Autowired
	EmailSender sendEmail;
	
	@Override
	public String generateCode() {
		String upperAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	    String lowerAlphabet = "abcdefghijklmnopqrstuvwxyz";
	    String numbers = "0123456789";
	    String alphaNumeric = upperAlphabet + lowerAlphabet + numbers;
	    StringBuilder sb = new StringBuilder();
	    Random random = new Random();
	    int length = 10;

	    for(int i = 0; i < length; i++) {
	      int index = random.nextInt(alphaNumeric.length());
	      char randomChar = alphaNumeric.charAt(index);
	      sb.append(randomChar);
	    }
		return sb.toString();
	}
	
	@Override
	public String addRendezVous(RendezVous r ,Long userId) {
		
		String code = generateCode();
		r.setCode(code); 
		User user = userRepo.findById(userId).orElse(null);
		r.setUser(user);
		int[] vaze=verifExistCalendr(r);
		int x = vaze[0];
		int idc=vaze[1];
		Long idC=(long) idc;
		Calendrier c = calendrRepo.findById(idC).orElse(null) ;
		if (verifRendezVousDateInput(r)==true && x==1) {
		rendezVousRepo.save(r);
		String link = "http://localhost:8080/WomenEmpowerment/RendezVous/confirm?code=" +code;
		sendEmail.sendrendv(c.getUser().getEmail(),buildEmailredv(c.getUser().getFirstName(),code, link) );
		sendMeetLink();
		return "t3addet";}
		else return "verif date input";
	}
	@Override
	public List<RendezVous> getAllRendezVous() {		
		return (List<RendezVous>) rendezVousRepo.findAll();
	}
	@Override
	public String updateRendezVous(RendezVous r,Long userId) {
		String code = generateCode();
		int[] vaze=verifExistCalendr(r);
		int x = vaze[0];
		int idc=vaze[1];
		Long idC=(long) idc;
		r.setCode(code); 
		r.setUser(userRepo.getById(userId));
		if (verifRendezVousDateInput(r)==true && x==1) {
			rendezVousRepo.save(r);
			return "t3addet";}
			else return "verif date input";
	}
	@Override
	public String deleteRendezVous(Long id) {
		rendezVousRepo.deleteById(id);
		return "Deleted";
	}
	@Override
	public boolean verifRendezVousDateInput(RendezVous r) {
		if(r.getDebut().compareTo(r.getFin())>=0) {return false;}
		else
		return true;
	}
	@Override
	public int[] verifExistCalendrMedcin(RendezVous r) {
		int[] vaze =new int[2];
		vaze[0]=0;
		vaze[1]=0;
		List<Calendrier> listCalendr =calendrService.getAllCalendr();
		for(Calendrier c : listCalendr) {
			if( c.getUser().getAppUserRole().name()=="MEDCIN") {
				if((c.getDebut().compareTo(r.getDebut()) <=0)&& (c.getFin().after(r.getFin()) )) {
					vaze[0]=1;
					vaze[1]= c.getId().intValue();
					return vaze;
				}
			}
		}
		
		return vaze;
	}
	@Override
	public int[] verifExistCalendrLawyer(RendezVous r) {
		int[] vaze =new int[2];
		vaze[0]=0;
		vaze[1]=0;
		List<Calendrier> listCalendr =calendrService.getAllCalendr();
		for(Calendrier c : listCalendr) {
			if( c.getUser().getAppUserRole().name()=="LAWYER") {
				if((c.getDebut().compareTo(r.getDebut()) <=0)&& (c.getFin().after(r.getFin()) )) {
					vaze[0]=1;
					vaze[1]= c.getId().intValue();
					return vaze;
				}
			}
		}
		return vaze;
	}
	@Override
	public int[] verifExistCalendrPsy(RendezVous r) {
		int[] vaze =new int[2];
		vaze[0]=0;
		vaze[1]=0;
		List<Calendrier> listCalendr =calendrService.getAllCalendr();
		for(Calendrier c : listCalendr) {
			if( c.getUser().getAppUserRole().name()=="PSY") {
				if((c.getDebut().compareTo(r.getDebut()) <=0)&& (c.getFin().after(r.getFin()) )) {
					vaze[0]=1;
					vaze[1]= c.getId().intValue();
					return vaze;
				}
			}
		}
		return vaze;
	}
	@Override
	public int[] verifExistCalendr(RendezVous r) {
		int[] vaze =new int[2];
		vaze[0]=0;
		vaze[1]=0;
		if(r.getRendezVousType().name()=="AVEC_MEDCIN") {
			return verifExistCalendrMedcin(r);
		}
		else if(r.getRendezVousType().name()=="AVEC_LAWYER") {
			return verifExistCalendrLawyer(r);
		}
		else if(r.getRendezVousType().name()=="AVEC_PSY") {
			return verifExistCalendrPsy(r);
		}
		else
		return vaze;
	}
	
	

	@Override
	public String confirm(String code) {
		rendezVousRepo.confirmRendezVous(code);
		return "Rendez-Vous confirmer ";
	}

	

	@Override
	public void sendMeetLink() {
		List<RendezVous> listR =(List<RendezVous>) rendezVousRepo.getConfirmedRendezVous();
		for (RendezVous r :listR) {
				String link = "https://meet.google.com/sbc-rxzj-qew" ;
				sendEmail.sendmeet(r.getUser().getEmail(),buildEmailMeet(r.getUser().getFirstName(),r.getCode(), link) );
		}
	}

	
	@Override
	public String buildEmailredv(String name,String code,  String link) {
		return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                "\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "\n" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
                "        \n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                "          <tbody><tr>\n" +
                "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td style=\"padding-left:10px\">\n" +
                "                  \n" +
                "                    </td>\n" +
                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Appointment confirmation</span>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "              </a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                "      <td>\n" +
                "        \n" +
                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi Expert " + name + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Here below you need to confirm your meet " + code + ". Please click on the below link to confirm it: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\"" + link + "\">Confirm Now</a> </p></blockquote>\n  <p>See you soon</p>" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "</div></div>";
	}
	
	@Override
	public String buildEmailMeet(String name, String code, String link) {
		return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                "\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "\n" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
                "        \n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                "          <tbody><tr>\n" +
                "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td style=\"padding-left:10px\">\n" +
                "                  \n" +
                "                    </td>\n" +
                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Join Meet </span>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "              </a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                "      <td>\n" +
                "        \n" +
                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#B0F2B6\">Hi " + name + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#B0F2B6\"> Here below you can access your meet " + code + ". Please click To join the meet </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#B0F2B6\"> <a href=\"" + link + "\">Join Now</a> </p></blockquote>\n  <p>See you soon</p>" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "</div></div>";
	}
	

}
