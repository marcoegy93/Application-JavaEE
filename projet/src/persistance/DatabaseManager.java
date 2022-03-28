package persistance;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import mediatek2022.Document;

public class DatabaseManager {
	private static Connection connection;
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
	         connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaee", "abdel","abdel" );
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Emprunt d'un document
	public static void empruntDoc(int idDoc , int idUser) {
		String reqDocEmprunt = "Update document set idUtil=?, dateEmprun = ? where id = ?;";
		PreparedStatement st;
		
		synchronized(connection){

		try {
			st = connection.prepareStatement(reqDocEmprunt);
			st.setInt(1, idUser);
			st.setDate(2, new Date(System.currentTimeMillis()));
			st.setInt(3, idDoc);
			
			st.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}}
	}
	
	
	//Retour d'un document 
	public static void retourDoc(int idDoc ) {
		String reqDocEmprunt = "Update document set idUtil=null, dateEmprun = null where id = ?;";
		PreparedStatement st;
		
		synchronized(connection){

		try {
			st = connection.prepareStatement(reqDocEmprunt);
			st.setInt(1, idDoc);
			
			st.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		}
	}
	
	
	//Retourne la liste de document emprunté par l'abonne
	public static Document [] docEmprunterParUser(int idUser) {
		ArrayList<Document> liste = new ArrayList<>();
		String reqDocEmprunt = "Select * from document where idUtil = ?;";
		PreparedStatement st;
		
		synchronized(connection){

		try {
			st = connection.prepareStatement(reqDocEmprunt);
			st.setInt(1, idUser);
			ResultSet resultatDoc = st.executeQuery();
			
			while(resultatDoc.next())
			 {
				
					Class<? extends Document> classe = Class.forName("persistance.document."+resultatDoc.getString("type")).asSubclass(Document.class);
					liste.add(classe.getConstructor(String.class,int.class,Double.class,String.class,String.class,Date.class).newInstance(resultatDoc.getString("titre"),resultatDoc.getInt("id"),resultatDoc.getDouble("prix"),resultatDoc.getString("auteur"),resultatDoc.getString("description"),resultatDoc.getDate("dateEmprun")));
			}
			

		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException | SecurityException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		//Convertion de la list en tableau pour ne plus pouvoir modifier cette liste 
		Document [] tabDoc = new Document [liste.size()];
		tabDoc = liste.toArray(tabDoc);
		return tabDoc;
		}
	}
	
	
}
