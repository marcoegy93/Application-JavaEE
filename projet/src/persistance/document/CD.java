package persistance.document;

import java.sql.Date;


public class CD extends DocumentAbs {
	
	
	public CD(String nom,int id,Double prix,String auteur,String description, Date dateEmprunt)  {
		super(nom,id,prix,auteur,description,dateEmprunt);
	}

}
