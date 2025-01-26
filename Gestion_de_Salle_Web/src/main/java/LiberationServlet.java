

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import metier.entities.EtatSalle;
import metier.entities.User;
import metier.HoraireLocal;
import metier.JourLocal;
import metier.JournalisationLocal;
import metier.LiberationLocal;
import metier.NotificationLocal;
import metier.SalleLocal;
import metier.UserLocal;


@WebServlet(name = "lib", urlPatterns = "*.lib")
public class LiberationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       @EJB
       private SalleLocal metierS;
       @EJB
       private UserLocal metierU;
       @EJB
       private HoraireLocal metierH;
       @EJB
       private JourLocal metierJ;
       @EJB 
       private LiberationLocal metierL;
       @EJB
   	private NotificationLocal metierN;
   	@EJB
   	private JournalisationLocal metierJou;
       
    public LiberationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession();
	    User user = (User) session.getAttribute("user");
	    if (user == null) {
	        res.sendRedirect("LogIn.jsp");
	        return;
	    }
		session.setAttribute("sallesP", metierS.filtreP(user.getId()));
		
		req.getRequestDispatcher("Liberation.jsp").forward(req, res);
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null) {
	        res.sendRedirect("LogIn.jsp");
	        return;
	    }
		System.out.println("User in session: " + user.getNom());
		String action = req.getParameter("action");
		String idetat=req.getParameter("id");
		Long id = Long.parseLong(idetat);
		EtatSalle es = metierS.getEtatById(id);

		System.out.println("ACTION : "+ action);
		switch(action) {
		case "Definitive":
			metierL.libererartionDefinitive(es,0);
			session.setAttribute("journal", metierJou.listJournalisation());
	         
			session.setAttribute("notifs", metierN.getNotifs(user));
		
			session.setAttribute("sallesP", metierS.filtreP(user.getId()));

			req.getRequestDispatcher("Liberation.jsp").forward(req, res);
			
			break;
		
		case "Exceptionnelle":
			session.setAttribute("id", id);
			
			req.getRequestDispatcher("LiberationExp.jsp").forward(req, res);
			break;
		case "Valider la liberation":
			String n = req.getParameter("nbr");
			int nbr = Integer.parseInt(n);
			metierL.libererationExp(es,nbr);
			session.setAttribute("journal", metierJou.listJournalisation());
	         
			session.setAttribute("notifs", metierN.getNotifs(user));
			session.removeAttribute("action");
			res.sendRedirect("Liberation.lib");
			break;
	}
		session.removeAttribute("action");	 
}
}
