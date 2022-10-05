package tn.esprit.spring.service;

import java.time.LocalDateTime;
import java.util.List;

import tn.esprit.spring.entity.CommentairePub;
import tn.esprit.spring.entity.Publication;
import tn.esprit.spring.entity.TypePub;

public interface ICommentairePubService {
	public String AjouterCommentare(CommentairePub com,Long idPub,String email) ;
	public List<CommentairePub> getCommentairePub();
	public void deleteCom(Long id);
	public void updateCom(CommentairePub comP);
	public int nbreLikeParCmnt(Long id);
	public List<CommentairePub> dasd(String title,String description,TypePub type,String users) ;
	public List<CommentairePub> Lisaa(Long pub) ;

}
