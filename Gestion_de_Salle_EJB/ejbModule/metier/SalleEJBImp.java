package metier;

import java.util.Collections;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import metier.entities.EtatSalle;
import metier.entities.Horaire;
import metier.entities.Jour;
import metier.entities.Salle;
import metier.entities.User;
@Stateless
@Local(SalleLocal.class)
public class SalleEJBImp implements SalleLocal,SalleRemote{

	@PersistenceContext(unitName = "SallesEJB")
    private EntityManager em;
	
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
	            etatSalle.setProf(null);
	            etatSalle.setEtatSalle(false);
	            EtatSalle es = em.merge(etatSalle);
	        }
	    }
	    
	    return salle;
	}

	public void modifierSalle(Long id, Salle s) {
		Salle salle = em.find(Salle.class, id);
		salle.setNomSalle(s.getNomSalle());
		salle.setCapacite(s.getCapacite());
		salle.setType(s.getType());
	}
	public void supprimerSalle(Long id) {
		Salle s = em.find(Salle.class, id);
		if(s!=null)
			em.remove(s);
	}
	
	public boolean getEtat(Long id, Horaire h, Jour j) {
		Query req = em.createQuery("SELECT s.etat FROM EtatSalle s WHERE s.salle = :s AND s.horaire = :h AND s.jour = :j");
		Salle s = em.find(Salle.class, id);
		req.setParameter("id", s);
        req.setParameter("j", j);
        req.setParameter("h", h);
        return (boolean) req.getSingleResult();
        
	}
	public void setEtat(Long id, Horaire h, Jour j , boolean e, User prof) {
		Query updateReq = em.createQuery(
			    "UPDATE EtatSalle s SET s.etat = :newEtat, s.prof=:prof  WHERE s.id_etat = :id AND s.horaire = :h AND s.jour = :j"
			);
		
		updateReq.setParameter("newEtat", e);
		updateReq.setParameter("id", id);
		updateReq.setParameter("j", j);
		updateReq.setParameter("h", h);
		updateReq.setParameter("prof", prof);
		updateReq.executeUpdate();
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
		Query q = em.createQuery("SELECT es FROM EtatSalle es WHERE es.prof = :p AND es.etat = true") ; // true=non vide
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
}
