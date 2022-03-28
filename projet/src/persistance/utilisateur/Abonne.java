package persistance.utilisateur;


public class Abonne extends UtilisateurAbs {

	
	public Abonne(String name,int id, Object...objects ) {
		super(name,id,objects);
	}
	
	@Override
	public boolean isBibliothecaire() {
		// TODO Auto-generated method stub
		return false;
		
	}

}
