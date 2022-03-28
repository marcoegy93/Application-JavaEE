package services;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mediatek2022.Mediatheque;
import mediatek2022.Utilisateur;



@WebServlet(urlPatterns = "/authentification", loadOnStartup = 1)


public class ServletRechercheUtilisateur extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	@Override
	public void init() throws ServletException {
		try {
			Class.forName("persistance.MediathequeData");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("*******************chargement de la servlet recherche utilisateur******************");
		super.init();
	}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletRechercheUtilisateur() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("message", "");
		RequestDispatcher d = request.getRequestDispatcher("./vue-jsp/authentification.jsp");
		d.forward(request, response);
		
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Utilisateur user=null;

		if(request.getParameter("login")!=null && request.getParameter("mdp")!= null) {
			String login = request.getParameter("login");
			String mdp = request.getParameter("mdp");
			 user = Mediatheque.getInstance().getUser(login, mdp);
		 if( user == null) {
				request.setAttribute("message", "Votre identifiant ou mot de passe est incorect !");
				RequestDispatcher d = request.getRequestDispatcher("./vue-jsp/authentification.jsp");
				d.forward(request, response);	
			}
			else {	
				try {
					
					if(!user.isBibliothecaire()) {
						
					response.sendRedirect(request.getContextPath()+"/locationDocument");
					request.getSession().setAttribute("user",user);
					
		
					}
					else {
						response.sendRedirect(request.getContextPath()+"/ajoutDocument");

					}
				} catch ( IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				
			}
		}
		
		}
	
	
	// sevlet = html vers java
	// jsp = java vers html

}
