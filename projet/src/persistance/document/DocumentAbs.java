package persistance.document;

import java.sql.Date;

import mediatek2022.Document;
import mediatek2022.Utilisateur;
import persistance.DatabaseManager;

public class DocumentAbs implements Document{
	
	private int id;
	private String titre;
	private Utilisateur user;
	private String auteur;
	private String description;
	private double prix;
	private Date dateEmprunt;
	
	public DocumentAbs(String titre,int id,Double prix,String auteur,String description, Date dateEmprunt) {
		this.titre = titre;
		this.id=id;
		this.auteur = auteur;
		this.description=description;
		this.prix=prix;
		this.dateEmprunt=dateEmprunt;
	}
	
	
	@Override
	public boolean disponible() {
		if(dateEmprunt == null) {
			return true;
		}
		return false;
	}

	@Override
	public void emprunt(Utilisateur u) throws Exception {
		
		if(!this.disponible())
			throw new Exception ("Document indisponible");
		
		this.user = u;
		int idUtil = Integer.parseInt(u.toString().split(" ")[0]);
		DatabaseManager.empruntDoc(id,idUtil);
	}

	@Override
	public void retour() {
		this.user = null;
		DatabaseManager.retourDoc(id);
	}
	
	@Override
	public String toString() {
		if(disponible())
		return id + "/" +titre + "/" + auteur + "/" + description +  "/" +prix + "$";
		else
			return id + "/" +titre + "/" + auteur + "/" + description + "/" + dateEmprunt +"/" + prix + "$";
	}
}
