package tn.esprit.spring.User;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.objdetect.Objdetect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


import nu.pattern.OpenCV;
import tn.esprit.spring.entity.FacebookData;
import tn.esprit.spring.entity.SuspiciousAccount;
import tn.esprit.spring.entity.UserEmotions;
import tn.esprit.spring.registration.token.ConfirmationToken;
import tn.esprit.spring.repository.SuspiciousAccountRepository;
import tn.esprit.spring.repository.UserEmotionsRepository;
import tn.esprit.spring.entity.FaceEntity;


@Service
public class UserManagement implements IUserManagement{
	
	@Autowired
	private UserRepository myRepository;
	
	@Autowired
	private SuspiciousAccountRepository suspiciousAccountRepository;
	
	@Autowired
	private UserEmotionsRepository userEmotionsRepository;

	@Override
	public User findByEmail(String email) {
		return myRepository.findByEmail(email).orElse(null);
	}
	
	@Override
	public List<User> getUsers() {
		return myRepository.findAll(Sort.by(Sort.Direction.DESC, "firstName"));
	}

	@Override
	public void deleteUser(String email) {
		User u = myRepository.findByEmail(email).orElse(null);
		myRepository.delete(u);
	}

	@Override
	public void updateUser(User user) {
			 myRepository.save(user);
	}

	@Override
	public List<User> findByFirstNameContains(String firstName) {
		return myRepository.findByFirstNameContains(firstName);
	}

	@Override
	public List<User> findByFirstNameOrLastNameContains(String firstName,String lastName) {
		
		return myRepository.findByFirstNameOrLastNameContains(firstName, lastName);
	}

	@Override
	public List<User> findByLocked(Boolean locked) {
		return myRepository.findByLocked(locked);
	}

	@Override
	public int unlockedAppUser(String email) {
		return myRepository.unlockedAppUser(email);
	}

	@Override
	public int lockedAppUser(String email) {
		return myRepository.lockedAppUser(email);
	}

	@Override
	public List<Object[]> countTotalUsersByYear() {
		return myRepository.countTotalUsersByYear();
	}

	
	
	static int countOccurrences(String str, String word)
	{
	    String a[] = str.split(" ");
	 
	    int count = 0;
	    for (int i = 0; i < a.length; i++)
	    {
	    if (word.equals(a[i]))
	        count++;
	    }
	 
	    return count;
	}
	
	String[] sadness = {"sad","sadness","deplorable","distressing","lamentable","pitiful","sorry","sadness","bittersweet","doleful","heavyhearted", "melancholic","melancholy",
			"mournful","pensive","tragic", "tragical","tragicomic","tragicomical","wistful"}; 
	String[] happiness = {"happiness","felicitous","cheerful","content","contented","elated","euphoric","glad","joyful","joyous","blessed","prosperous","halcyon",
	        "riant","laughing","blissful","bright","golden"}; 
	String[] suicide = {"self-annihilation","self-destruction","killing","death","kill","suicidal","slayer","killer"}; 
	String[] violence = {"ferocity","killing","death","kill","fierceness","furiousness","fury","vehemence","wildness","savagery","savageness"}; 
	String[] fear ={"revere","reverence","venerate", "fearfulness","fright"};
	String[] anger ={"rage","angry","angriness","enragement","fury","hackles", "huffiness","dander","indignation","annoyance", "infuriation", "chafe","bad temper","vexation", "madness","umbrage", "offence","offense", "rage", "outrage"};
	@Override
	public Map<String, Float> UserPrediction(String email) {
		int nbgood = 0;
		int nbbad = 0;
		int nbsuicide = 0;
		int nbviolence = 0;
		
		int nbfear = 0;
		int nbanger = 0;
		int all =0;
		Map<String, Float> mapOfLists = new HashMap<String, Float>();
		User u = myRepository.findByEmail(email).orElse(null);
		Set<FacebookData> facebookData = u.getFacebookData();
		
		for (FacebookData facebookData2 : facebookData) {
			String[] words = facebookData2.getMessage().split("\\s+");
			all = all+words.length;
		}
		//System.out.println(all);
		for (FacebookData facebookData2 : facebookData) {
			for(String str : sadness){
				nbbad = nbbad+countOccurrences(facebookData2.getMessage(),str);
				
			}
			
			
			for(String str : happiness){
				nbgood = nbgood+countOccurrences(facebookData2.getMessage(),str);
				//System.out.println(nbgood); 
			}
			
			
			for(String str : suicide){
				nbsuicide = nbsuicide+countOccurrences(facebookData2.getMessage(),str);
				//System.out.println(nbgood); 
			}
			for(String str : violence){
				nbviolence = nbviolence+countOccurrences(facebookData2.getMessage(),str);
				//System.out.println(nbgood); 
			}
			
			for(String str : fear){
				nbfear = nbfear+countOccurrences(facebookData2.getMessage(),str);
				//System.out.println(nbgood); 
			}
			for(String str : anger){
				nbanger = nbanger+countOccurrences(facebookData2.getMessage(),str);
				//System.out.println(nbgood); 
			}
			
		}
		UserEmotions UE = new UserEmotions();
		UE.setUser(u);
		UE.setHappy((float) nbgood*100/all);
		UE.setSad((float) nbbad*100/all);
		UE.setViolence((float) nbviolence*100/all);
		UE.setSuicide((float) nbsuicide*100/all);
		UE.setFear((float) nbfear/all*100);
		UE.setAnger( (float) nbanger*100/all);
		
		
		
		
		userEmotionsRepository.save(UE);
		mapOfLists.put("Sad",(float) nbbad*100/all);
		mapOfLists.put("Happy", (float) nbgood*100/all);
		mapOfLists.put("Suicide", (float) nbsuicide*100/all);
		mapOfLists.put("Violence", (float) nbviolence*100/all);
		mapOfLists.put("Fear", (float) nbfear*100/all);
		mapOfLists.put("Anger", (float) nbanger*100/all);

		
		return mapOfLists;
	}

	private Resource faceResource = new ClassPathResource("haarcascades/haarcascade_frontalface_alt.xml");
    private List<FaceEntity> faceEntities;
    private Mat image;
    

	 public List<FaceEntity> toList() {
	        return faceEntities;
	    }


	    public byte[] toImage() {
	        for (FaceEntity fc : faceEntities) {
	            Imgproc.rectangle(image, new Point(fc.getX(), fc.getY()), new Point(fc.getX() + fc.getWidth(), fc.getY() + fc.getHeight()), new Scalar(0, 255, 0));
	        }
	        return mat2Image(image);
	    }

	    private byte[] mat2Image(Mat frame) {
	        MatOfByte buffer = new MatOfByte();
	        Imgcodecs.imencode(".jpg", frame, buffer);
	        return buffer.toArray();
	    }

	@Override
	public UserManagement fakeAccounts(MultipartFile file) {
		
		 faceEntities=new ArrayList<>();
	        MatOfRect faceDetections = new MatOfRect();
	        CascadeClassifier faceDetector;
			
		
		try {
	       
				faceDetector = new CascadeClassifier(faceResource.getFile().getAbsolutePath());
				image = Imgcodecs.imdecode(new MatOfByte(file.getBytes()), Imgcodecs.CV_LOAD_IMAGE_UNCHANGED);
		
	        faceDetector.detectMultiScale(image, faceDetections);

	        System.out.println(String.format("Detected %s faces", faceDetections.toArray().length));

	        for (Rect rect : faceDetections.toArray()) {
	            faceEntities.add(new FaceEntity(rect.x, rect.y, rect.width, rect.height, 0));
	        }}	 catch (IOException e) {
				e.printStackTrace();
			}
	        
	        
		List<User> userList = myRepository.findAll();
		float suspicious = 0;
		String test="";

		for (User user : userList) {
			SuspiciousAccount sa = new SuspiciousAccount();
			sa.setUser(user);

			
			String fn = user.getFirstName();
			String ln = user.getLastName();
			Pattern p = Pattern.compile("[^a-zA-Z]");
			Pattern p1 = Pattern.compile("[^a-zA-Z09]");
			
			if(user.getAge()>80 || user.getAge()<14 ){
				sa.setAge("age does not meet the requirements");
				suspicious = (float) (suspicious+0.20);
			}else{
				sa.setAge("Verified");
			}
			
			if(user.getReporting().size()>20){
				suspicious = (float) (suspicious+0.20);
				sa.setReported("the reporting number is suspicious");
				
			}
			else{
				sa.setReported("Verified");
			}
			
			
			
			if(p.matcher(fn).find() || p.matcher(fn).find()){
				suspicious = (float) (suspicious+0.10);
				sa.setFistlastname("name does not meet the requirements");
			}
			else{
				sa.setFistlastname("Verified");
			}
			
			
			Set<ConfirmationToken> ct = user.getConfirmationTokens(); 
			if(ct.size()>10){
				suspicious = (float) (suspicious+0.10);
				sa.setTokensus("the Confirmation Tokens number is suspicious");
				
			}else{
				sa.setTokensus("Verified");
			}
			
			if(p1.matcher(user.getAdress()).find()){
				suspicious = (float) (suspicious+0.10);
				sa.setAdre("Adress does not meet the requirements");
				
			}else{
				sa.setAdre("Verified");
			}
			
			if(user.getPublications().size() ==0 ||user.getPublications().size() >20){
				suspicious = (float) (suspicious+0.10);
				sa.setPub("the Publications number is suspicious");
			}else{
				sa.setPub("Verified");
			}
			
			if(faceDetections.toArray().length==0){
				sa.setPic("profile pic does not meet the requirements");
				suspicious = (float) (suspicious+0.20);
			}else{
				sa.setPic("Verified");
			}
			sa.setPercentage(suspicious);
			suspiciousAccountRepository.save(sa);
	
		}
		return this;
		
	}

	@Override
	public List<SuspiciousAccount> getFakeAccounts() {
		return suspiciousAccountRepository.findAll();
	}

	@Override
	public void deleteFakeAccounts(Long id) {
		suspiciousAccountRepository.deleteById(id);;
		
	}



}
