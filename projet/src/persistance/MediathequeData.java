package persistance;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import mediatek2022.*;

// classe mono-instance  dont l'unique instance est connue de la médiatheque
// via une auto-déclaration dans son bloc static
// pas besoin de id dans al fonction ajout car dans al bdd on utilise un autoincrementeur

public class MediathequeData implements PersistentMediatheque {
// Jean-François Brette 01/01/2018
	
	
	private static Connection connection;
	
	static { //il est executé au chargement de la classe java
		//on dit à Médiatheque d'initialiser ton objet data avec MediathequeData	
		Mediatheque.getInstance().setData(new MediathequeData());
		
	}

	private MediathequeData() {
		super();
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
	         connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaee", "abdel","abdel" );
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	// renvoie la liste de tous les documents disponibles de la médiathèque
	@Override
	public List<Document> tousLesDocumentsDisponibles(){
		List<Document> listeDocument = new ArrayList<Document>();
		String reqAllDocuments = "Select * from Document where idUtil is null;";
		synchronized(connection){
		try {
			Statement st = connection.createStatement();
			ResultSet resultatsDocuments = st.executeQuery(reqAllDocuments);
			
			while(resultatsDocuments.next()) {		 
				
					Class<? extends Document> classe = Class.forName("persistance.document."+resultatsDocuments.getString("type")).asSubclass(Document.class);
					listeDocument.add(classe.getConstructor(String.class,int.class,Double.class,String.class,String.class,Date.class ).newInstance(resultatsDocuments.getString("titre"),resultatsDocuments.getInt("id"),resultatsDocuments.getDouble("prix"),resultatsDocuments.getString("auteur"),resultatsDocuments.getString("description"),resultatsDocuments.getDate("dateEmprun")));
				
			}
			
		}catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException | SecurityException  |SQLException e) {
			e.printStackTrace();
			}
		}
		return listeDocument;
	}
		

	// va récupérer le User dans la BD et le renvoie
	// si pas trouvé, renvoie null
	@Override
	public Utilisateur getUser(String login, String password) {
		Utilisateur user=null;

		String reqUser = "Select * from Utilisateur where login= ? and mdp = ?;";
		PreparedStatement st;
	
		synchronized(connection){

		try {
			st = connection.prepareStatement(reqUser);
			st.setString(1, login);
			st.setString(2, password);
			st.executeQuery();
			ResultSet resultatUser = st.executeQuery();
			if(resultatUser.next()) {
				Class<? extends Utilisateur> classe = Class.forName("persistance.utilisateur."+resultatUser.getString("type")).asSubclass(Utilisateur.class);
				user = classe.getConstructor(String.class,int.class,Object[].class).newInstance(resultatUser.getString("login"), resultatUser.getInt("idUtil"),(Object)new Object[] { resultatUser.getString("login"), resultatUser.getString("age"),resultatUser.getString("sexe"),resultatUser.getDate("dateCreationCompte")});
				return user;
			}
		} catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}
		
		return user;
	}

	// va récupérer le document de numéro numDocument dans la BD
	// et le renvoie
	// si pas trouvé, renvoie null
	@Override
	public Document getDocument(int numDocument) {
		Document doc=null;
		String reqDoc = "Select * from Document where id= ?;";
		PreparedStatement st;
		
		synchronized(connection){

		try {
			st = connection.prepareStatement(reqDoc);
			st.setInt(1, numDocument);
			ResultSet resultatDoc = st.executeQuery();
			if(resultatDoc.next())
			 {
				Class<? extends Document> classe = Class.forName("persistance.document."+resultatDoc.getString("type")).asSubclass(Document.class);
				doc = classe.getConstructor(String.class,int.class,Double.class,String.class,String.class,Date.class ).newInstance(resultatDoc.getString("titre"),resultatDoc.getInt("id"),resultatDoc.getDouble("prix"),resultatDoc.getString("auteur"),resultatDoc.getString("description"),resultatDoc.getDate("dateEmprun"));
			}
		} catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		}
		return doc;
	}

	@Override
	public void ajoutDocument(int type, Object... args) {
		
		// args[0] -> le titre
		// args [1] --> prix
		// args [2] --> type
		// args [3] --> auteur
		// args [4] --> description


		String reqUser = "Insert into document(titre,prix,type,auteur,description) values (?,?,?,?,?);";
		PreparedStatement st;
		
		synchronized(connection){

		try {
			st = connection.prepareStatement(reqUser);
			st.setString(1, (String)args[0]);
			st.setDouble(2,  Double.parseDouble((String) args[1]));
			st.setString(3, (String)args[2]);
			st.setString(4,(String)args[3]);
			st.setString(5,(String)args[4]);

			st.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	}

}
