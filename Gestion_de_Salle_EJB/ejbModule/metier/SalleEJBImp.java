package metier;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import metier.entities.Horaire;
import metier.entities.Jour;
import metier.entities.Salle;
@Stateless
@Local(SalleLocal.class)
public class SalleEJBImp implements SalleLocal,SalleRemote{

	@PersistenceContext(unitName = "SallesEJB")
    private EntityManager em;
	
	public List<Salle> listSalles(){
		Query req = em.createQuery("select s from salle s");
        return req.getResultList();
	}
	public Salle getSalle(Long id) {
		Salle pr = em.find(Salle.class, id);
        if (pr == null) {
            throw new IllegalArgumentException("Salle introuvable");
        }
        return pr;
    }
	public Salle ajouterSalle(Salle s) {
		  return em.merge(s);
	}
	public void modifierSalle(Long id, Salle s) {
		Salle salle = em.find(Salle.class, id);
		salle.setNomSalle(s.getNomSalle());
		salle.setCapacite(s.getCapacite());
	}
	public void supprimerSalle(Long id) {
		Salle s = em.find(Salle.class, id);
		if(s!=null)
			em.remove(s);
	}
	
	public boolean getEtat(Long id, Horaire h, Jour j) {
		Query req = em.createQuery("SELECT s.etat FROM etat_salle s WHERE s.id_salle = :id AND s.id_horaire = :h AND s.id_jour = :j");
		req.setParameter("id", id);
        req.setParameter("j", j.getIdJour());
        req.setParameter("h", h.getIdHoraire());
        return (boolean) req.getSingleResult();
        
	}
	public void setEtat(Long id, Horaire h, Jour j , boolean e) {
		Query updateReq = em.createQuery(
			    "UPDATE etat_salle s SET s.etat = :newEtat WHERE s.id_salle = :id AND s.id_horaire = :h AND s.id_jour = :j"
			);
		updateReq.setParameter("newEtat", e);
		updateReq.setParameter("id", id);
		updateReq.setParameter("j", j.getIdJour());
		updateReq.setParameter("h", h.getIdHoraire());
		
	}
	public List<Salle> getSallesVidesByHoraireNDJour(Horaire h,Jour j){
		Query q = em.createQuery("SELECT *\r\n"
				+ "FROM salle\r\n"
				+ "INNER JOIN etat_salle ON salle.id_salle = etat_salle.id_salle\r\n"
				+ "WHERE etat_salle.id_horaire=:h and etat_salle.id_jour=:j;"
				+ "AND etat_salle.etat = false;") ;
		return q.getResultList();	}
	
	public List<Salle> getSallesVides(){
		Query q = em.createQuery("SELECT *\r\n"
				+ "FROM salle\r\n"
				+ "INNER JOIN etat_salle ON salle.id_salle = etat_salle.id_salle\r\n"
				+ "WHERE etat_salle.etat = false;") ;
		return q.getResultList();	}
	@Override
	public List<Salle> filtreJH(Horaire h, Jour j) {
		Query q = em.createQuery("SELECT *\r\n"
				+ "FROM salle\r\n"
				+ "INNER JOIN etat_salle ON salle.id_salle = etat_salle.id_salle\r\n"
				+ "WHERE etat_salle.id_horaire=:h and etat_salle.id_jour=:j") ;
		q.setParameter("h", h);
		q.setParameter("j", j);
		return q.getResultList();
	}

	public List<Salle> filtreJ(Jour j){
		Query q = em.createQuery("SELECT *\r\n"
				+ "FROM salle\r\n"
				+ "INNER JOIN etat_salle ON salle.id_salle = etat_salle.id_salle\r\n"
				+ "WHERE etat_salle.id_jour=:j") ;
		q.setParameter("j", j);
		return q.getResultList();
	}
	public List<Salle> filtreH(Horaire h){
		Query q = em.createQuery("SELECT *\r\n"
				+ "FROM salle\r\n"
				+ "INNER JOIN etat_salle ON salle.id_salle = etat_salle.id_salle\r\n"
				+ "WHERE etat_salle.id_horaire=:h") ;
		q.setParameter("h", h);
		return q.getResultList();
	}

}
