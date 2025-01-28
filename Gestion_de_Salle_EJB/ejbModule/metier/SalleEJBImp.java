package metier;

import java.util.Collections;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import jakarta.transaction.Transactional;
import metier.entities.Categorie;
import metier.entities.EtatSalle;
import metier.entities.Filiere;
import metier.entities.Horaire;
import metier.entities.Jour;
import metier.entities.Matiere;
import metier.entities.MatiereFiliereCategorie;
import metier.entities.Salle;
import metier.entities.User;
@Stateless
@Local(SalleLocal.class)
public class SalleEJBImp implements SalleLocal,SalleRemote{

	@PersistenceContext(unitName = "SallesEJB")
    private EntityManager em;
	
	@EJB
	JournalisationLocal metier;
	@EJB
	LiberationLocal metierL;
	public List<Salle> listSalles(){
		Query req = em.createQuery("select s from Salle s");
        return req.getResultList();
	}
	public Salle getSalle(Long id) {
		Salle salle = em.find(Salle.class, id);
        if (salle == null) 
            throw new IllegalArgumentException("Salle introuvable");
        
        return salle;
    }
	
	public Salle ajouterSalle(Salle s) {
	    Salle salle = em.merge(s);  

	    List<Horaire> horaires = em.createQuery("SELECT h FROM Horaire h", Horaire.class).getResultList();
	    List<Jour> jours = em.createQuery("SELECT j FROM Jour j", Jour.class).getResultList();

	    for (Jour jour : jours) {
	        for (Horaire horaire : horaires) {
	            EtatSalle etatSalle = new EtatSalle();
	            etatSalle.setSalle(salle);  
	            etatSalle.setHoraire(horaire);     
	            etatSalle.setJour(jour); 
	            etatSalle.setMatiere(null);
	            etatSalle.setEtatSalle(false);
	            EtatSalle es = em.merge(etatSalle);
	        }
	    }
	    metier.journaliser("Ajout d'une salle, nommée : "+salle.getNomSalle() + 
	    		" pour les séances de" + salle.getType().getType_categorie() 
	    		+ ", sa capacité est : " + salle.getCapacite());

	    return salle;
	}

	public void modifierSalle(Long id, Salle s) {
		Salle salle = em.find(Salle.class, id);
		salle.setNomSalle(s.getNomSalle());
		salle.setCapacite(s.getCapacite());
		salle.setType(s.getType());
		metier.journaliser("Modification de la salle: "+salle.getNomSalle());
	}
	public void supprimerSalle(Long id) {
		Salle s = em.find(Salle.class, id);
		if(s!=null)
			metier.journaliser("Suppression de la salle: "+ s.getNomSalle());
			em.remove(s);
	}
	
	public boolean getEtat(Long id, Horaire h, Jour j) {
		        TypedQuery<Boolean> req = em.createQuery(
		            "SELECT s.etat FROM EtatSalle s WHERE s.salle.id_salle = :id AND s.horaire.id_horaire = :h AND s.jour.id_jour = :j",
		            Boolean.class
		        );
		        req.setParameter("id", id);
		        req.setParameter("h", h.getIdHoraire());
		        req.setParameter("j", j.getIdJour());
		        return req.getSingleResult();
		    }
	public void setEtat(Long id, boolean e, MatiereFiliereCategorie mat) {
		
			EtatSalle salle = em.find(EtatSalle.class, id);
			salle.setEtatSalle(e);
			String etat = e==true? "occupée par ":"libérée par ";
			metier.journaliser("La salle :  "+salle.getSalle().getNomSalle() +" est " + etat + mat.getProf().getNom() + " pour le " + salle.getJour().getNomJour() + " dans l'horaire "+ salle.getHoraire().getNomHoraire());
			if(!e) 
				mat=null;

			salle.setMatiere(mat);
	}
	public List<EtatSalle> getSallesVides(){
		Query q = em.createQuery("SELECT es FROM EtatSalle es WHERE es.etat = false") ;
		return q.getResultList();	}
	@Override
	public List<EtatSalle> filtreJH(Horaire h, Jour j) {
		Query q = em.createQuery("SELECT es FROM EtatSalle es WHERE es.horaire=:h and es.jour=:j") ;
		q.setParameter("h", h);
		q.setParameter("j", j);
		return q.getResultList();
	}

	public List<EtatSalle> filtreJ(Jour j){
		Query q = em.createQuery("SELECT es FROM EtatSalle es WHERE es.jour=:j") ;
		q.setParameter("j", j);
		return q.getResultList();
	}
	public List<EtatSalle> filtreH(Horaire h){
		Query q = em.createQuery("SELECT es FROM EtatSalle es WHERE es.horaire=:h") ;
		q.setParameter("h", h);
		return q.getResultList();
	}

	public List<EtatSalle> filtreP(int id){
		Query q = em.createQuery("SELECT es FROM EtatSalle es WHERE es.matiere.prof = :p AND es.etat = true") ; // true=non vide
		User prof = em.find(User.class, id);
		q.setParameter("p", prof);
		return q.getResultList();
	}
	@Override

	public List<EtatSalle> filtreEtatNom(String nom) {
	    try {
	        Query q = em.createQuery(
	            "SELECT es FROM EtatSalle es JOIN es.salle s WHERE s.nom_salle = :nom"
	        );
	        q.setParameter("nom", nom);
	        return q.getResultList();
	    } catch (NoResultException e) {
	        System.out.println("No EtatSalle found for salle: " + nom);
	        return Collections.emptyList();
	    } catch (Exception e) {
	        e.printStackTrace();
	        return Collections.emptyList();
	    }
	}

	@Override
	public List<EtatSalle> filtrePJH(Long id, Horaire h, Jour j) {
		Query q = em.createQuery("SELECT es " +
			    "FROM EtatSalle es " + 
			    "WHERE es.prof = :p AND es.etat = true and es.horair = :h and es.jour = :j") ; // true=non vide
		User prof = em.find(User.class, id);
		q.setParameter("p", prof);
		q.setParameter("h", h);
		q.setParameter("j", j);
		return q.getResultList();
	}

	public List<EtatSalle> filtrePJ(Long id, Jour j){
		Query q = em.createQuery("SELECT es " +
			    "FROM EtatSalle es " + 
			    "WHERE es.prof = :p AND es.etat = true and es.jour = :j") ; // true=non vide
		User prof = em.find(User.class, id);
		q.setParameter("p", prof);
		q.setParameter("j", j);
		return q.getResultList();
	}
	public List<EtatSalle> filtrePH(Long id, Horaire h){
		Query q = em.createQuery("SELECT es " +
			    "FROM EtatSalle es " + 
			    "WHERE es.prof = :p AND es.etat = true and es.horair = :h ") ; // true=non vide
		User prof = em.find(User.class, id);
		q.setParameter("p", prof);
		q.setParameter("j", h);
		return q.getResultList();
	}

	public List<EtatSalle> getEtatSalles(){
		Query q = em.createQuery("SELECT es FROM EtatSalle es");
		return q.getResultList();
	}
	public EtatSalle getEtatById(Long id) {
		EtatSalle es = em.find(EtatSalle.class, id);
		return es;
	}
	public void setProf(Long idEtat, User prof) {
		Query q = em.createQuery("UPDATE EtatSalle s SET s.prof = :p where s.id_etat=:id");
		q.setParameter("p", prof);
		q.setParameter("id", idEtat);
		q.executeUpdate();
	}
	
@Override
     public List<Salle> getSallesByCategorie(int id_categorie) {
       TypedQuery<Salle> query = em.createQuery(
           "SELECT s FROM Salle s WHERE s.categorie.id = :id", Salle.class);
       query.setParameter("id", id_categorie);
       return query.getResultList();
   }
public List<Salle> getSalleByName(String n) {
	Query q = em.createQuery("select s from Salle s where s.nom_salle=:n");
	q.setParameter("n", n);
	return q.getResultList();
}
public void setMFC(Long id, MatiereFiliereCategorie m) {
	em.createQuery("update EtatSalle e set e.matiere=:m where e.id_etat=:id")
	.setParameter("m", m).setParameter("id", id).executeUpdate();
}

public void calculChargeHor() {;
	List<EtatSalle> salles = em.createQuery("select es from EtatSalle es where es.etat=true").getResultList();
	for(EtatSalle salle : salles) {
		if(salle.getMatiere().getNbrHrAct()>0)
			salle.getMatiere().setNbrHrAct(salle.getMatiere().getNbrHrAct()-2);
		if(salle.getMatiere().getNbrHrAct()<=0) {
			metierL.libererartionDefinitive(salle,0);
		}
	}
}

@Override
public void setEtat(Long id, Horaire h, Jour j, boolean e, MatiereFiliereCategorie mfc) {
    try {

        if (h == null ||  j == null ) {
            throw new IllegalArgumentException("Les paramètres horaire, jour ou mfc ne doivent pas être null.");
        }


        Query updateReq = em.createQuery(
            "UPDATE EtatSalle s " +
            "SET s.etat= :newEtat, s.matiere = :mfc " +
            "WHERE s.salle.id_salle = :id " +
            "AND s.horaire = :h " +
            "AND s.jour = :j"
        );


        updateReq.setParameter("newEtat", e);
        updateReq.setParameter("mfc", mfc);
        updateReq.setParameter("id", id);
        updateReq.setParameter("h", h);
        updateReq.setParameter("j", j);


        int rowsUpdated = updateReq.executeUpdate();
        if (rowsUpdated == 0) {
            throw new IllegalArgumentException("Aucune ligne mise à jour. Vérifiez les paramètres fournis.");
        }
        em.flush(); 
        System.out.println("Mise à jour réussie. Lignes affectées : " + rowsUpdated);

    } catch (Exception ex) {
        ex.printStackTrace(); 
        throw new RuntimeException("Erreur lors de la mise à jour de l'état : " + ex.getMessage(), ex);
    }
}
@Override
public List<EtatSalle> getSallesVidesByHoraireNDJourAndCat(Categorie cat, Horaire h, Jour j) {
    TypedQuery<EtatSalle> q = em.createQuery(
        "SELECT es FROM EtatSalle es " +
        "WHERE es.horaire = :h AND es.jour = :j AND es.salle.categorie = :cat AND es.etat = false",
        EtatSalle.class
    );
    q.setParameter("h", h);
    q.setParameter("j", j);
    q.setParameter("cat", cat);
    return q.getResultList();
}

@Override
public List<EtatSalle> getLesSallesVidesByCategorie(Categorie cat) {
    TypedQuery<EtatSalle> q = em.createQuery(
        "SELECT es FROM EtatSalle es " +
        "WHERE es.salle.categorie = :cat AND es.etat = false",
        EtatSalle.class
    );
    q.setParameter("cat", cat);
    return q.getResultList();
}
@Override
public void setEtat(Long id_salle, Horaire horaire, Jour jour, boolean e) {
	// TODO Auto-generated method stub
	
}

}
