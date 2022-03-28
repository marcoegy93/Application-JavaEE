package persistance.utilisateur;

import java.util.Arrays;

import mediatek2022.Utilisateur;
import persistance.DatabaseManager;



///Pas oublier le nom du user dans retourdocument
public abstract class UtilisateurAbs implements Utilisateur {

	private int id ;
	private String name;
	private Object[] data;
	
	public UtilisateurAbs(String name,int id, Object...objects) {
		this.name=name;
		this.id=id;
		this.data = Arrays.copyOf(objects,objects.length+1);
	}
	@Override
	public String name() {
		return this.name;
	}

	@Override
	public abstract boolean isBibliothecaire();

	@Override
	public Object[] data() {
		
		//retourn data et la liste des document emprunté par l'utilisateur
		data[data.length-1]=DatabaseManager.docEmprunterParUser(id);
		return data;
	}
	
	 @Override
	 public String toString() {
		 return id + " " + name + " ";
	 }

}