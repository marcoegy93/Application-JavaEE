package persistance.document;

import java.sql.Date;

import mediatek2022.Document;
import mediatek2022.Utilisateur;

public class livre extends DocumentAbs{

	public livre(String nom,int id,Double prix,String auteur,String description, Date dateEmprunt)  {
		super(nom,id,prix,auteur,description,dateEmprunt);
	}
	
}
