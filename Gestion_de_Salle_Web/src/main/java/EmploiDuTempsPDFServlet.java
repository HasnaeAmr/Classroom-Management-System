
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;

import metier.CategorieLocal;
import metier.FiliereLocal;
import metier.MatiereFiliereCategorieLocal;
import metier.MatiereLocal;
import metier.SalleLocal;
import metier.UserLocal;
import metier.entities.Categorie;
import metier.entities.Filiere;
import metier.entities.Matiere;
import metier.entities.User;
import metier.entities.Salle;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import metier.entities.MatiereFiliereCategorie;

@WebServlet(name ="emploi", urlPatterns ="*.em") 
public class EmploiDuTempsPDFServlet extends HttpServlet {

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
    @EJB
    private SalleLocal salle;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	HttpSession session = req.getSession();
    	
    	List<User> profs = UserEJB.ListProf();
        List<Filiere> filieres = filiere.listFiliere();
        List<Matiere> matieres = matiere.listMatiere();
        List<Categorie> categories = categorie.listCategorie();
        
        session.setAttribute("filieres", filieres);
        session.setAttribute("matieres", matieres);
        session.setAttribute("categories", categories);
        session.setAttribute("profs", profs);
        
        req.getRequestDispatcher("EmploiTemps.jsp").forward(req, res);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
    	 String action = req.getParameter("action");
         
         HttpSession session = req.getSession();
        
         Integer selectedFiliere = (Integer) session.getAttribute("selectedId");  
         

         if (action.equals("Valider")) {
            
             String filiereId = req.getParameter("Idfiliere");
             if (filiereId != null && !filiereId.isEmpty()) {
            	 try { selectedFiliere = Integer.parseInt(filiereId); 
                 session.setAttribute("selectedId", selectedFiliere);
                 List<MatiereFiliereCategorie> mfcs = metier.listMFCByFiliere(selectedFiliere);
                   
                 session.setAttribute("mfcs", mfcs);
            	 } catch (NumberFormatException e) {
 	                req.setAttribute("errorMessage","invalide idfiliere");
 	            }
 	        } else {
 	            req.setAttribute("errorMessage", "Veuillez sélectionner une filiere.");
 	        }

             req.getRequestDispatcher("EmploiTemps.jsp").forward(req, resp);
         }
         else if(action.equals("Remplir")) {
        	    if (selectedFiliere != null) {
        	        req.setAttribute("selectedId", selectedFiliere);
        	        List<MatiereFiliereCategorie> mfcs = metier.listMFCByFiliere(selectedFiliere);
        	       
     
        	        if (mfcs == null || mfcs.isEmpty()) {
        	            System.out.println("Aucune matière trouvée pour la filière : " + selectedFiliere);
        	        } 
        	        req.setAttribute("mfcs", mfcs);
        	        String jour=req.getParameter("jour");
        	        String heure=req.getParameter("heure");
        	        req.setAttribute("jour", jour);
        	        req.setAttribute("heure", heure);
        	       
        	        if (jour == null ) {
        	            System.out.println("jour n'est pas recu : " + jour);
        	        } 
        	        req.getRequestDispatcher("Remplir.jsp").forward(req, resp);
        	    }
        	}
         else if(action.equals("Reset")) {
     	    if (selectedFiliere != null) {
     	        req.setAttribute("selectedId", selectedFiliere);
     	
     	        String jour=req.getParameter("jour");
     	        String heure=req.getParameter("heure");
     	        req.setAttribute("jour", jour);
     	        req.setAttribute("heure", heure);
     	       Map<String, Map<String, String>> emploiDuTemps = (Map<String, Map<String, String>>) session.getAttribute("emploiDuTemps");
       	    if (emploiDuTemps == null) {
       	        emploiDuTemps = new HashMap<>();
       	    }
     	       String key=jour + "_" + heure;
     	      if (emploiDuTemps.containsKey(key)) {
	                Map<String, String> details = emploiDuTemps.get(key);
	                emploiDuTemps.remove(key); }
     	        req.getRequestDispatcher("EmploiTemps.jsp").forward(req, resp);
     	    }
     	}
         else if(action.equals("resetable")) {
      	    if (selectedFiliere != null) {
      	    	Map<String, Map<String, String>> emploiDuTemps = (Map<String, Map<String, String>>) session.getAttribute("emploiDuTemps");
      	    	String[] heures = {"8:30 - 10:20", "10:40 - 12:30", "14:30 - 16:20", "16:40 - 18:30"};
        	    String[] jours = {"Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi"};
      	       
        	    if (emploiDuTemps == null) {
        	        emploiDuTemps = new HashMap<>();
        	    }
        	    for (String jour : jours) {
        	        
        	        for (String heure : heures) {
        	            String key = jour + "_" + heure;
        	            if (emploiDuTemps.containsKey(key)) {
        	                Map<String, String> details = emploiDuTemps.get(key);
        	                emploiDuTemps.remove(key); 
        	               
        	           
        	        }
        	    }}
      	        req.getRequestDispatcher("EmploiTemps.jsp").forward(req, resp);
      	    }
      	}
         else if (action.equals("RechercherMFC")) {
        	    if (selectedFiliere != null) {
        	        req.setAttribute("selectedId", selectedFiliere);
        	        String jour=req.getParameter("jour");
        	        String heure=req.getParameter("heure");
        	        req.setAttribute("jour", jour);
        	        req.setAttribute("heure", heure);
        	        String nomMatiere = req.getParameter("nom_matiere");
        	        String nomCategorie = req.getParameter("nom_categorie");

        	        if (nomMatiere != null && nomCategorie != null && !nomMatiere.isEmpty() && !nomCategorie.isEmpty()) {
        	            try {
        	                int idMatiere = Integer.parseInt(nomMatiere);
        	                int idCategorie = Integer.parseInt(nomCategorie);

        	                MatiereFiliereCategorie mfc = metier.MFCID(selectedFiliere, idMatiere, idCategorie);
        	                List<Salle> salleselected = salle.getSallesByCategorie(idCategorie);

        	                
        	                if (salleselected != null && !salleselected.isEmpty()) {
        	                    req.setAttribute("salleselected", salleselected);
        	                    session.setAttribute("salleselected", salleselected); 
        	                } else {
        	                    req.setAttribute("errorMessage", "Aucune salle trouvée.");
        	                }

        	                req.setAttribute("mfc", mfc);
        	                req.setAttribute("categorieselected", idCategorie);
        	                req.setAttribute("matiereselected", idMatiere);

        	            } catch (NumberFormatException e) {
        	                req.setAttribute("errorMessage", "Les identifiants sont invalides.");
        	            }
        	        } else {
        	            req.setAttribute("errorMessage", "Veuillez sélectionner une matière et une catégorie.");
        	        }

        	        req.getRequestDispatcher("Remplir.jsp").forward(req, resp);
        	    }
        	}

     
			/*
			 * else if (action.equals("ChercherSalle")) { if (selectedFiliere != null) {
			 * req.setAttribute("selectedId", selectedFiliere);
			 * 
			 * String categorieselected = req.getParameter("categorieselected"); if
			 * (categorieselected == null || categorieselected.isEmpty()) {
			 * req.setAttribute("error", "Veuillez sélectionner une catégorie.");
			 * req.getRequestDispatcher("index.jsp").forward(req, resp); // Redirige vers
			 * une page valide return; }
			 * 
			 * try { int idCategorie = Integer.parseInt(categorieselected);
			 * 
			 * List<Salle> salleselected = salle.getSallesByCategorie(idCategorie);
			 * 
			 * if (salleselected == null || salleselected.isEmpty()) {
			 * req.setAttribute("error",
			 * "Aucune salle n'est disponible pour la catégorie sélectionnée."); } else {
			 * req.setAttribute("salleselected", salleselected); }
			 * 
			 * req.setAttribute("selectedCategorie", idCategorie);
			 * req.getRequestDispatcher("ChoisirSalle.jsp").forward(req, resp);
			 * 
			 * } catch (NumberFormatException e) { req.setAttribute("error",
			 * "Le format de la catégorie est incorrect.");
			 * req.getRequestDispatcher("index.jsp").forward(req, resp); // Redirige vers
			 * une page valide } } else { req.setAttribute("error",
			 * "Aucune filière sélectionnée.");
			 * req.getRequestDispatcher("index.jsp").forward(req, resp); // Redirige vers
			 * une page valide } }
			 * 
			 * else if(action.equals("Choisir")) { if (selectedFiliere != null) {
			 * selectedFiliere=Integer.parseInt(req.getParameter("selectedId")); int idSalle
			 * = Integer.parseInt(req.getParameter("selectedSalle")); int idCategorie =
			 * Integer.parseInt(req.getParameter("selectedCategorie"));
			 * req.setAttribute("selectedCategorie", idCategorie);
			 * req.setAttribute("selectedSalle", idSalle ); req.setAttribute("selectedId",
			 * selectedFiliere); req.getRequestDispatcher("Remplir.jsp").forward(req, resp);
			 * } }
			 */
         else if(action.equals("Confirmer")) {
        	 if (selectedFiliere != null) {
                 req.setAttribute("selectedId", selectedFiliere);
                 int idfiliere=Integer.parseInt(req.getParameter("selectedId"));
                 Long idSalle = Long.parseLong(req.getParameter("salleselected"));
     	         String Categorie = req.getParameter("categorie");
     	         String Matiere = req.getParameter("matiere");
     	         String nbr_heure = req.getParameter("nbr-heure");
     	         String prof=req.getParameter("prof");
     	         String Salle=((SalleLocal) salle).getSalle(idSalle).getNomSalle();
     	         String jour = req.getParameter("jour"); 
     	         String heure = req.getParameter("heure");
     	         
     	        req.setAttribute("id", idfiliere);
                req.setAttribute("salle", idSalle);
     	        req.setAttribute("categorie", Categorie);
                req.setAttribute("matiere", Matiere);
                req.setAttribute("nbr_heure", nbr_heure);
                req.setAttribute("prof", prof);
                req.setAttribute("jour", jour);
    	        req.setAttribute("heure", heure);
                
                Map<String, String> emploi = new HashMap<>();
                emploi.put("matiere", Matiere);
                emploi.put("categorie", Categorie);
                emploi.put("nbr_heure", nbr_heure);
                emploi.put("prof", prof);
                emploi.put("salle", Salle);
                
                
               String key = jour + "_" + heure;
                
                // Stocker les informations dans la session
                Map<String, Map<String, String>> emploiDuTemps = (Map<String, Map<String, String>>) session.getAttribute("emploiDuTemps");
                if (emploiDuTemps == null) {
                    emploiDuTemps = new HashMap<>();
                }
                emploiDuTemps.put(key, emploi);
                
                
                session.setAttribute("emploiDuTemps", emploiDuTemps);
                 req.getRequestDispatcher("EmploiTemps.jsp").forward(req, resp);
        	 }
         }
         
    
        
         else if(action.equals("Telecharger")) {
        	    req.setAttribute("selectedId", selectedFiliere);
        	    
        	    // Récupérer l'emploi du temps depuis la session
        	    Map<String, Map<String, String>> emploiDuTemps = (Map<String, Map<String, String>>) session.getAttribute("emploiDuTemps");
        	    if (emploiDuTemps == null) {
        	        emploiDuTemps = new HashMap<>();
        	    }
        	    //crearation du fichier pdf dans la memoire:
        	    ByteArrayOutputStream pdfStream = new ByteArrayOutputStream();
        	    PdfWriter writer = new PdfWriter(pdfStream);
        	    PdfDocument pdfDocument = new PdfDocument(writer);
        	    Document document = new Document(pdfDocument);

        	    document.add(new Paragraph("Emploi du Temps" + "\n" + "Filiere: " + filiere.getFiliereById(selectedFiliere).getNom_filiere()));

        	            

        	    String[] heures = {"8:30 - 10:20", "10:40 - 12:30", "14:30 - 16:20", "16:40 - 18:30"};
        	    String[] jours = {"Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi"};

        	    Table table = new Table(heures.length + 1);
        	    table.setWidth(UnitValue.createPercentValue(100));
        	    table.addCell(new Cell().add(new Paragraph("Jour/Heure").setBold()).setTextAlignment(TextAlignment.CENTER));
        	    for (String heure : heures) {
        	        table.addCell(new Cell().add(new Paragraph(heure).setBold()).setTextAlignment(TextAlignment.CENTER));
        	    }

        	    for (String jour : jours) {
        	        table.addCell(new Cell().add(new Paragraph(jour).setTextAlignment(TextAlignment.CENTER)));
        	        for (String heure : heures) {
        	            String key = jour + "_" + heure;
        	            if (emploiDuTemps.containsKey(key)) {
        	                Map<String, String> details = emploiDuTemps.get(key);
        	                String value = details.get("matiere") + "\n" +
        	                               details.get("categorie") + " \n" +
        	                               details.get("nbr_heure") + "H \n" +
        	                               details.get("prof") + " \n " +
        	                               details.get("salle");
        	                table.addCell(new Cell().add(new Paragraph(value)).setTextAlignment(TextAlignment.CENTER));
        	            } else {
        	                table.addCell(new Cell().add(new Paragraph("")).setTextAlignment(TextAlignment.CENTER));
        	            }
        	        }
        	    }

        	    document.add(table);
        	    document.close();

        	    resp.setContentType("application/pdf");
        	    resp.setHeader("Content-Disposition", "attachment; filename=emploi_du_temps.pdf");
        	    resp.setContentLength(pdfStream.size());
        	    resp.getOutputStream().write(pdfStream.toByteArray());
        	    resp.getOutputStream().flush();
        	}

    }
}

