
import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import metier.CategorieLocal;
import metier.FiliereLocal;
import metier.MatiereFiliereCategorieLocal;
import metier.MatiereLocal;
import metier.UserLocal;
import metier.entities.MatiereFiliereCategorie;
import metier.entities.User;
import metier.entities.Filiere;
import metier.entities.Matiere;
import metier.entities.Categorie;

@WebServlet(name = "MFC", urlPatterns = "*.do")
public class MatiereFiliereCategorieServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @EJB
    private MatiereFiliereCategorieLocal metier;
    @EJB
    private UserLocal UserEJB;
    @EJB
    private FiliereLocal filiere;
    @EJB
    private MatiereLocal matiere;
    @EJB
    private CategorieLocal categorie;


    public MatiereFiliereCategorieServlet() {
        super();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	 HttpSession session = req.getSession();
    	 Integer selectedFiliere = (Integer) session.getAttribute("selectedId");
        List<User> profs = UserEJB.ListProf();
        List<Filiere> filieres = filiere.listFiliere();
        List<Matiere> matieres = matiere.listMatiere();
        List<Categorie> categories = categorie.listCategorie();
        session.setAttribute("filieres", filieres);
        session.setAttribute("matieres", matieres);
        session.setAttribute("categories", categories);
        session.setAttribute("profs", profs);
        session.setAttribute("selectedId", selectedFiliere);

        
        req.getRequestDispatcher("MFC.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        
        HttpSession session = req.getSession();
        List<MatiereFiliereCategorie> mfcs = (List<MatiereFiliereCategorie>) session.getAttribute("mfcs");
        Integer selectedFiliere = (Integer) session.getAttribute("selectedId");  

        if (action.equals("Valider")) {
           
            String filiereId = req.getParameter("Idfiliere");
            if (filiereId != null && !filiereId.isEmpty()) {
                selectedFiliere = Integer.parseInt(filiereId); 
                session.setAttribute("selectedId", selectedFiliere); 
            }

            updateSessionMFC(session, selectedFiliere);
            req.getRequestDispatcher("MFC.jsp").forward(req, resp);
        } else if (action.equals("AjouterMFC")) {
            if (selectedFiliere != null) {
                req.setAttribute("selectedId", selectedFiliere);
                req.getRequestDispatcher("AjouterMFC.jsp").forward(req, resp);
            }
        } else if (action.equals("Ajouter")) {
            if (selectedFiliere != null) {
                try {
                    ajouterMFC(req, selectedFiliere);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                updateSessionMFC(session, selectedFiliere);
                resp.sendRedirect("MFC.jsp");
            }
         else {
	        req.setAttribute("erreur", "Veuillez selectionner une filiere avant L'ajout.");
	        req.getRequestDispatcher("MFC.jsp").forward(req, resp);
	    }
        }
               else  if(action.equals("ModifierMFC")){
                            int code = Integer.parseInt(req.getParameter("codeToUpdate"));
                            MatiereFiliereCategorie mfc=metier.getMFC(code);
                            req.setAttribute("mfc", mfc);
                            req.setAttribute("codeToUpdate", code);
                            req.getRequestDispatcher("ModifierMFC.jsp").forward(req, resp);
                           
                        }
               else  if(action.equals("Modifier")){
                        	int code= Integer.parseInt(req.getParameter("codeToUpdate"));
                        	MatiereFiliereCategorie mfc=metier.getMFC(code);
                            try {
								modifierMFC(req, code);
							} catch (Exception e) {
								
								e.printStackTrace();
							}
                            updateSessionMFC(session, mfc.getFiliere().getId_filiere());
                            resp.sendRedirect("MFC.jsp");
                           
                        }
               else if (action.equals("Rechercher")) {
            	    
            	     selectedFiliere = (Integer) session.getAttribute("selectedId");
            	    
            	    if (selectedFiliere != null) {
            	        String code = req.getParameter("NomArechercher");
            	        List<MatiereFiliereCategorie> searchResults = metier.getMFCList(code);
            	        session.setAttribute("mfcs", searchResults);
            	        session.setAttribute("selectedId", selectedFiliere);
            	        resp.sendRedirect("MFC.jsp");
            	    } else {
            	        req.setAttribute("erreur", "Veuillez selectionner une filiere avant La Recherche.");
            	        req.getRequestDispatcher("MFC.jsp").forward(req, resp);
            	    }
            	}

               else if(action.equals("Supprimer")){
                            int codeToDelete = Integer.parseInt(req.getParameter("codeToDelete"));
                            MatiereFiliereCategorie mfc=metier.getMFC(codeToDelete);
                            metier.removeMFC(codeToDelete);
                            updateSessionMFC(session, mfc.getFiliere().getId_filiere());
                            resp.sendRedirect("MFC.jsp");
                            
                        }
                        
                          
                            
                    
                
              
               }
            

            private void updateSessionMFC(HttpSession session, int selectedFiliere) {
                List<MatiereFiliereCategorie> mfcs = metier.listMFCByFiliere(selectedFiliere);
                session.setAttribute("mfcs", mfcs);
            }

            private void ajouterMFC(HttpServletRequest req, int selectedFiliere) throws Exception {
            	 try {
            	        int idMatiere = Integer.parseInt(req.getParameter("nom_matiere"));
            	        int idCategorie = Integer.parseInt(req.getParameter("nom_categorie"));
            	        int nbrHeure = Integer.parseInt(req.getParameter("nbr-heure"));
            	        int idProf = Integer.parseInt(req.getParameter("nom"));

            	      

            	       
            	        metier.AddMCByFiliere(selectedFiliere, idMatiere, idCategorie, nbrHeure, idProf);

            	        
            	        System.out.println("MFC successfully added!");
            	    } catch (Exception e) {
            	        
            	        System.err.println("Error adding MFC: " + e.getMessage());
            	        throw e;
            	    }
            	}
            
            private void modifierMFC(HttpServletRequest req, int code) throws Exception {
            	
                int idMatiere = Integer.parseInt(req.getParameter("nom_matiere"));
                int idCategorie = Integer.parseInt(req.getParameter("nom_categorie"));
                int nbrHeure = Integer.parseInt(req.getParameter("nbr-heure"));
                int idProf = Integer.parseInt(req.getParameter("nom"));

                metier.setMFCByFiliere(code, idMatiere, idCategorie, nbrHeure, idProf);
            }


}


