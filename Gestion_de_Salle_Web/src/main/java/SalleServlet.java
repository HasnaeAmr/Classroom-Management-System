import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import metier.CategorieLocal;
import metier.SalleLocal;
import metier.entities.Categorie;
import metier.entities.Salle;

@WebServlet(name = "sal", urlPatterns = "*.salle")
public class SalleServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String ACTION_AJOUTER = "Ajouter";
    private static final String ACTION_MODIFIER = "Modifier";
    private static final String ACTION_SUPPRIMER = "Supprimer";

    @EJB
    private SalleLocal metier;
    @EJB
    private CategorieLocal metierC;

    private void refreshSession(HttpSession session) {
        session.setAttribute("salles", metier.listSalles());
        session.setAttribute("categories", metierC.listCategorie());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        refreshSession(session); 
        request.getRequestDispatcher("Salle.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String action = req.getParameter("action");

        
            switch (action) {
            
                case ACTION_AJOUTER:
                	try {
                        String idCatStr = req.getParameter("type");
                        if (idCatStr == null || idCatStr.isEmpty()) {
                            throw new IllegalArgumentException("Category ID is missing");
                        }
                        
                        int idCat = Integer.parseInt(idCatStr);  
                    
                    String nom = req.getParameter("nom");
                    String capaciteStr = req.getParameter("capacite");

                    if (nom == null || capaciteStr == null) {
                        throw new IllegalArgumentException("Name or capacity is missing");
                    }

                    int capacite = Integer.parseInt(capaciteStr);
                    Categorie categorie = metierC.getCategorie(idCat);
                    Salle salle = new Salle(nom, capacite, categorie);

                    metier.ajouterSalle(salle);
                    refreshSession(session);
                	} catch (NumberFormatException e) {
                        session.setAttribute("error", "Invalid number format: " + e.getMessage());
                        e.printStackTrace();  
                    } catch (IllegalArgumentException e) {
                        session.setAttribute("error", e.getMessage());
                    }
                    break;
                case ACTION_SUPPRIMER:
                		try{
                			String idStr = req.getParameter("idSalle");
                        if (idStr == null || idStr.isEmpty()) {
                            throw new IllegalArgumentException("Salle ID is missing");
                        }
                	
                	Long id = Long.parseLong(idStr);
                	metier.supprimerSalle(id);
                    refreshSession(session);
            } catch (NumberFormatException e) {
                session.setAttribute("error", "Invalid Salle ID format: " + e.getMessage());
                e.printStackTrace();  
            } catch (IllegalArgumentException e) {
                session.setAttribute("error", e.getMessage());
            }
            break;
            case ACTION_MODIFIER:
            	try {
                    
                String idStr = req.getParameter("idSalle");
                if (idStr == null || idStr.isEmpty()) 
                    throw new IllegalArgumentException("Salle ID is missing");
                Long id = Long.parseLong(idStr);
                Salle salle = metier.getSalle(id);
                req.setAttribute("salle", salle);  
                req.getRequestDispatcher("ModifierSalle.jsp").forward(req, res); 
                String nomModifie = req.getParameter("nomModifie");
                String cm= req.getParameter("capaciteModofie");
                int capacite = Integer.parseInt(cm);
                String idCatStr = req.getParameter("type");
                if (idCatStr == null || idCatStr.isEmpty()) {
                    throw new IllegalArgumentException("Category ID is missing");
                }
                int idCat = Integer.parseInt(idCatStr); 
                Categorie categorie = metierC.getCategorie(idCat);
                Salle salleModifie = new Salle(nomModifie, capacite, categorie);
                metier.modifierSalle(id, salleModifie);
                refreshSession(session);
                
                
                } catch (NumberFormatException e) {
                    session.setAttribute("error", "Invalid Salle ID format: " + e.getMessage());
                    e.printStackTrace();  
                } catch (IllegalArgumentException e) {
                    session.setAttribute("error", e.getMessage());
                }

            	break;
                default:
                    throw new IllegalArgumentException("Invalid action: " + action);
            }
            
        

        req.getRequestDispatcher("Salle.jsp").forward(req, res);
    }
}
