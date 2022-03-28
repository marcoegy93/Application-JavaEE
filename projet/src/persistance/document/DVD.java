package persistance.document;

import java.sql.Date;



public class DVD extends DocumentAbs{

	public DVD(String nom,int id,Double prix,String auteur,String description, Date dateEmprunt)  {
		super(nom,id,prix,auteur,description,dateEmprunt);
	}

	
}
