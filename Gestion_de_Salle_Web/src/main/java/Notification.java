

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import metier.JournalisationLocal;
import metier.NotificationLocal;
import metier.entities.User;

/**
 * Servlet implementation class Notification
 */
@WebServlet(name = "notif", urlPatterns = "*.notif")
public class Notification extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private NotificationLocal metierN;
    public Notification() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession();
	    User user = (User) session.getAttribute("user");
	    session.setAttribute("notifs", metierN.getNotifs(user));
		req.getRequestDispatcher("Notification.jsp").forward(req, res);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
