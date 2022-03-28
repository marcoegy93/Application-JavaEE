package services;

import java.io.IOException;

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


@WebServlet("/retourDocument")

public class ServletRetourDocument extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	@Override
	public void init() throws ServletException {
		try {
			Class.forName("persistance.MediathequeData");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("*******************chargement de la servlet retourDoucument******************");
		super.init();
	}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletRetourDocument() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utilisateur user = (Utilisateur) request.getSession().getAttribute("user");
		request.setAttribute("data", user.data());
		RequestDispatcher d = request.getRequestDispatcher("./vue-jsp/retourDocument.jsp");
		d.forward(request, response);	
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Utilisateur user = (Utilisateur) request.getSession().getAttribute("user");

		if( request.getParameter("choix").equals("retour")) {
			Document doc = Mediatheque.getInstance().getDocument((Integer.parseInt(request.getParameter("idDoc"))));
			try {
				Mediatheque.getInstance().retour(doc);
				request.setAttribute("retourEffectue", true);
	
			} catch (Exception e) {
				request.setAttribute("retourEffectue", false);
			}
			request.setAttribute("data", user.data());
			RequestDispatcher d = request.getRequestDispatcher("./vue-jsp/retourDocument.jsp");
			d.forward(request, response);		
		}
		
		if(request.getParameter("choix").equals("emprunt")) {
			response.sendRedirect(request.getContextPath()+"/locationDocument");
			request.getSession().setAttribute("user",user);
		}
		if(request.getParameter("choix").equals("deconnexion")) {
			response.sendRedirect(request.getContextPath()+"/authentification");
			HttpSession session = request.getSession();
			session.invalidate();
			 session = request.getSession(false);
		}

	
	}
	
}
