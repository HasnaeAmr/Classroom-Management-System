

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import metier.UserLocal;
import metier.entities.User;



@WebServlet(name = "p", urlPatterns = "*.p")
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     @EJB 
     UserLocal metierU;
    public ProfileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String nom = request.getParameter("nom_user");
		String mdp = request.getParameter("mot_de_passe");
		String idstr = request.getParameter("id");
		int id = Integer.parseInt(idstr);
		User user = metierU.getUser(id);
		user.setMdp(mdp);
		user.setNom(nom);
		session.setAttribute("user", user);
        request.getRequestDispatcher("Profile.jsp").forward(request, response);
	}

}
