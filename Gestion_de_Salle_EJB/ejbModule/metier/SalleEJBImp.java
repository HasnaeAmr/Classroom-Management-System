package metier;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
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
        if (salle == null) {
            throw new IllegalArgumentException("Salle introuvable");
        }
        return salle;
    }
	public Salle ajouterSalle(Salle s) {
		Salle salle = em.merge(s);
		Query h =  em.createQuery("SELECT h FROM Horaire h");
		List<Horaire> horaires = (List<Horaire>) h.getResultList();
		Query j = em.createQuery("SELECT j FROM Jour j");
		List<Jour> jours = (List<Jour>) j.getResultList();

		    // pour les combinaisons entre les jours et les horaires dans etat_salle
		    for (Jour jour : jours) {
		    	for(Horaire horaire : horaires) {
		    		EtatSalle etatSalle = new EtatSalle();
		            etatSalle.setSalle(salle);  
		            etatSalle.setHoraire(horaire);     
		            etatSalle.setJour(jour);            
		        }}
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
	public void setEtat(Long id, Horaire h, Jour j , boolean e) {
		Query updateReq = em.createQuery(
			    "UPDATE EtatSalle s SET s.etat = :newEtat WHERE s.salle = :s AND s.horaire = :h AND s.jour = :j"
			);
		Salle s = em.find(Salle.class, id);
		updateReq.setParameter("newEtat", e);
		updateReq.setParameter("s", s);
		updateReq.setParameter("j", j);
		updateReq.setParameter("h", h);
		
	}
	public List<EtatSalle> getSallesVides(){
		Query q = em.createQuery("SELECT es FROM EtatSalle es WHERE es.etat = false") ;
		return q.getResultList();	}
	@Override
	public List<EtatSalle> filtreJH(Horaire h, Jour j) {
		Query q = em.createQuery("SELECT es FROM EtatSalle WHERE es.horaire=:h and es.jour=:j") ;
		q.setParameter("h", h);
		q.setParameter("j", j);
		return q.getResultList();
	}

	public List<EtatSalle> filtreJ(Jour j){
		Query q = em.createQuery("SELECT es FROM EtatSalle WHERE es.jour=:j") ;
		q.setParameter("j", j);
		return q.getResultList();
	}
	public List<EtatSalle> filtreH(Horaire h){
		Query q = em.createQuery("SELECT es FROM EtatSalle WHERE es.horaire=:h") ;
		q.setParameter("h", h);
		return q.getResultList();
	}

	public List<EtatSalle> filtreP(int id){
		Query q = em.createQuery("SELECT es FROM EtatSalle WHERE es.prof = :p AND es.etat = true") ; // true=non vide
		User prof = em.find(User.class, id);
		q.setParameter("p", prof);
		return q.getResultList();
	}
	@Override

	public EtatSalle filtreEtatNom(String nom) {
		Query q = em.createQuery("SELECT s FROM Salle s WHERE s.nom_salle = :nom"); 
		q.setParameter("nom", nom);
		Salle salle = (Salle) q.getSingleResult();
		Query qu = em.createQuery("SELECT es FROM EtatSalle es WHERE es.salle = :s"); 
		q.setParameter("s", salle);
		EtatSalle es = (EtatSalle) qu.getSingleResult();
		return es;
		
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
		Query q = em.createQuery("SELECT es FROM EtatSalle");
		return q.getResultList();
	}
}
