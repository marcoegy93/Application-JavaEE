package services;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mediatek2022.Mediatheque;


@WebServlet("/ajoutDocument")

public class ServletAjoutDocument  extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	@Override
	public void init() throws ServletException {
		try {
			Class.forName("persistance.MediathequeData");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("*******************chargement de la servlet ajout document******************");
		super.init();
	}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAjoutDocument() {
        super();
    }

    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("message", "");

		RequestDispatcher d = request.getRequestDispatcher("./vue-jsp/ajoutDocument.jsp");
		d.forward(request, response);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			if( request.getParameter("choix").equals("ajout")) {
				if(request.getParameter("titre")=="" || request.getParameter("prix")=="" || request.getParameter("typeDoc") =="")
					request.setAttribute("message", "Des informations importantes n'ont pas été rensegne");
			else {
				Mediatheque.getInstance().ajoutDocument(0, request.getParameter("titre"),request.getParameter("prix"),request.getParameter("typeDoc"),request.getParameter("auteur"),request.getParameter("description") );
				request.setAttribute("message", "Document ajoute a la mediatheque !");
			}
			
			RequestDispatcher d = request.getRequestDispatcher("./vue-jsp/ajoutDocument.jsp");
			d.forward(request, response);
		}
		
		if(request.getParameter("choix").equals("deconnexion")) {
			response.sendRedirect(request.getContextPath()+"/authentification");
		}

	}
	
	
	
}
