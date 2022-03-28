package persistance.utilisateur;


public class bibliothecaire extends UtilisateurAbs{


	public bibliothecaire (String name,int id, Object...objects ) {
		super(name,id,objects);
	}
	

	@Override
	public boolean isBibliothecaire() {
		// TODO Auto-generated method stub
		return true;
	}

}
