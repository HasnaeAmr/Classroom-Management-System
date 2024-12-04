

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import metier.FiliereLocal;
import metier.entities.Filiere;


@WebServlet(name ="filiere", urlPatterns ="*.fs")
public class FiliereServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    
	
	  @EJB 
	  private FiliereLocal metier;
	 

  public FiliereServlet() {
      super();
  }

  protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
  		throws ServletException, IOException {
	  HttpSession session = req.getSession();
	  List<Filiere> filieres = metier.listFiliere();
	  req.setAttribute("filieres", filieres); 
	  req.getRequestDispatcher("Filiere.jsp").forward(req, resp);

  }

  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
  		throws ServletException, IOException {
      String action = req.getParameter("action");
      HttpSession session = req.getSession();
      List<Filiere> filieres = metier.listFiliere();
      
      if (action == null || action.trim().isEmpty()) {
          // Cas où il n'y a pas d'action spécifiée (rechargement ou page par défaut)
          updateSessionFilieres(session);
          req.getRequestDispatcher("Filiere.jsp").forward(req, resp);
          return;
      }
      
      //L'Ajout d'une Filiere:
      
      else if (action.equals("AjouterFiliere")) 
      {		req.getRequestDispatcher("AjouterFiliere.jsp").forward(req, resp);
      		
      		
      }
      else if(action.equals("Ajouter")) {
			
    	  String nom_filiere = req.getParameter("nom_filiere");
          String effectif = req.getParameter("effectif");
          Filiere fi = new Filiere(nom_filiere, effectif);
          metier.AddFiliere(fi);

          updateSessionFilieres(session);
          resp.sendRedirect("Filiere.jsp");
          return;
		  //if (!filieres.contains(addFi)) { filieres.add(addFi); }

		
  		} 
      
     else if (action.equals("ModifierFiliere")) 
    	 
          {	

    	 int code = Integer.parseInt(req.getParameter("codeToUpdate"));
         Filiere filiere = metier.getFiliereById(code);
         req.setAttribute("filiere", filiere);
         req.setAttribute("codeToUpdate", code);
         req.getRequestDispatcher("ModifierFiliere.jsp").forward(req, resp);
         return;}

     else if(action.equals("Modifier")) {
    	 int code = Integer.parseInt(req.getParameter("codeToUpdate"));
         String nomFiliere = req.getParameter("nom_filiere");
         String effectif = req.getParameter("effectif");
         metier.setFiliere(code, nomFiliere, effectif);
         updateSessionFilieres(session);
         resp.sendRedirect("Filiere.jsp");
         return;
					/*
					 * filieres.forEach(fi -> { if (fi.getId_filiere() == code) {
					 * fi.setNom_filiere(nom_filiere); fi.setEffectif(effectif); } });
					 */
    			
    	     	}
    	   
     else if (action.equals("RechercherFiliere")) {
    	    String nomFiliere = req.getParameter("NomFiliereArechercher");

    	    if (nomFiliere == null || nomFiliere.trim().isEmpty()) {
    	        req.setAttribute("erreur", "Veuillez saisir un nom à rechercher.");
    	        req.getRequestDispatcher("Filiere.jsp").forward(req, resp);
    	        return;
    	    }

    	    Filiere filiereTrouvee = metier.getFiliere(nomFiliere);

    	    if (filiereTrouvee != null) {
    	        // Affiche uniquement la filière trouvée
    	        req.setAttribute("filieres", List.of(filiereTrouvee));
    	    } else {
    	        // Affiche un message d'erreur et aucune filière
    	        req.setAttribute("filieres", List.of());
    	        req.setAttribute("erreur", "Aucune filière trouvée pour le nom : " + nomFiliere);
    	    }

    	    req.getRequestDispatcher("Filiere.jsp").forward(req, resp);
    	}

      else if(action.equals("SupprimerFiliere")) {
      	int code = Integer.parseInt(req.getParameter("codeToDelete"));
      	   metier.removeFiliere(code);
          // filieres.removeIf(fi->fi.getId_filiere()==code);
           updateSessionFilieres(session);
           resp.sendRedirect("Filiere.jsp");
          
      }

    
  
      
  }

  private void updateSessionFilieres(HttpSession session) {
	 List<Filiere> filieres = metier.listFiliere();
     session.setAttribute("filieres", filieres);
	}
  

}
