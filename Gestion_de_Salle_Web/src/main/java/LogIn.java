

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import metier.SalleLocal;
import metier.UserLocal;
import metier.entities.EtatSalle;
import metier.entities.Salle;
import metier.entities.User;

/**
 * Servlet implementation class LogIn
 */
@WebServlet(name = "log", urlPatterns = "*.log")
public class LogIn extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 @EJB
	    private UserLocal metierU;
	 @EJB
	 private SalleLocal metierS;
	
    public LogIn() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession();
		session.setAttribute("sallesV", metierS.getSallesVides());
		session.setAttribute("sallesD", metierS.getEtatSalles());
		req.getRequestDispatcher("LogIn.jsp").forward(req, res);
    }
	
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String nom = req.getParameter("nom");
		String psswd = req.getParameter("psswd");
		User user = metierU.checkUser(nom,psswd);
		
		if(user!=null) {
			session.setAttribute("user", user);
			session.setAttribute("sallesP", metierS.filtreP(user.getId()));
			//when null , handle input alert there ...
		if(user.getRole().getIdRole()==1)
			req.getRequestDispatcher("Fil.jsp").forward(req, res);
		else if(user.getRole().getIdRole()==2)
			req.getRequestDispatcher("Salle.jsp").forward(req, res);
		else if(user.getRole().getIdRole()==3)
			req.getRequestDispatcher("DashboardProf.jsp").forward(req, res);
	}
		else {
			req.getRequestDispatcher("LogIn.jsp").forward(req, res);
		}
		
	}
}
