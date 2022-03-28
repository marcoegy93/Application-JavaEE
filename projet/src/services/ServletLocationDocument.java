package services;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mediatek2022.Document;
import mediatek2022.Mediatheque;
import mediatek2022.Utilisateur;


@WebServlet("/locationDocument")


public class ServletLocationDocument  extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	@Override
	public void init() throws ServletException {
		try {
			Class.forName("persistance.MediathequeData");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("*******************chargement de la servlet location document******************");
		super.init();
	}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletLocationDocument() {
        super();
    }

    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Document> docList =  Mediatheque.getInstance().tousLesDocumentsDisponibles();
		request.setAttribute("listeDoc", docList);
		RequestDispatcher d = request.getRequestDispatcher("./vue-jsp/empruntDocument.jsp");
		d.forward(request, response);	
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utilisateur user = (Utilisateur) request.getSession().getAttribute("user");
		
		if( request.getParameter("choix").equals("emprunt")) {
		Document doc = Mediatheque.getInstance().getDocument((Integer.parseInt(request.getParameter("idDoc"))));
		try {
			Mediatheque.getInstance().emprunt(doc, user);
			request.setAttribute("empruntEffectuer", true);
		} catch (Exception e) {
			request.setAttribute("empruntEffectuer", false);
		}
		List<Document> docList =  Mediatheque.getInstance().tousLesDocumentsDisponibles();
		request.setAttribute("listeDoc", docList);
		RequestDispatcher d = request.getRequestDispatcher("./vue-jsp/empruntDocument.jsp");
		d.forward(request, response);	
		}
		
		if( request.getParameter("choix").equals("retour")) {
			response.sendRedirect(request.getContextPath()+"/retourDocument");
		}
		if(request.getParameter("choix").equals("deconnexion") ) {
			response.sendRedirect(request.getContextPath()+"/authentification");
			HttpSession session = request.getSession();
			session.invalidate();
			session = request.getSession(false);
		}
		}
	
	
	// sevlet = html vers java
	// jsp = java vers html

}
