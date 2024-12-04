 package metier;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import metier.entities.Filiere;
import metier.JournalisationLocal;


@Stateless(name="Filiere")
 @Local(FiliereLocal.class)
 @Remote(FiliereRemote.class)
public class FiliereEJBImp implements FiliereLocal, FiliereRemote {
	@PersistenceContext(unitName = "SallesEJB")
	private EntityManager em;
	@EJB
	JournalisationLocal jr;
	public FiliereEJBImp() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public Filiere AddFiliere(Filiere fi) {
		em.persist(fi);
		jr.journaliser("Ajout de la filière: " + fi.getNom_filiere() + ", ID: " + fi.getId_filiere());
		return fi;
	}
	@Override
	public Filiere getFiliere(String nom_filiere) {
		  try {
		        return em.createQuery("SELECT f FROM Filiere f WHERE f.nom_filiere = :nom", Filiere.class)
		                 .setParameter("nom", nom_filiere)
		                 .getSingleResult();
		    } catch (Exception e) {
		        throw new RuntimeException("Filiere introuvable avec le nom : " + nom_filiere, e);
		    }
		  
	}
	@Override
	public List<Filiere> listFiliere(){
		Query req=em.createQuery("select f from Filiere f");
		return req.getResultList();
	}
	@Override
	public void setFiliere(int id_filiere,String nom_filiere,String effectif) {
		Filiere fi=em.find(Filiere.class, id_filiere);
		
		fi.setNom_filiere(nom_filiere);
		fi.setEffectif(effectif);
		jr.journaliser("Modificaion de la filière: " + " ID: " + fi.getId_filiere()+"En :"+fi.getNom_filiere() +""+fi.getEffectif());
	}
	@Override
	public void removeFiliere(int id_filiere) {
		Filiere fi=em.find(Filiere.class, id_filiere);
		em.remove(fi);
		jr.journaliser("Suppression de la filiere " + fi.getNom_filiere() + ", ID: " + fi.getId_filiere());
		
		// test:
		System.out.println("deleted");
	}
	
	@Override
	public Filiere getFiliereById(int id_filiere) {
		Filiere fi=em.find(Filiere.class, id_filiere);
		if (fi==null) throw new RuntimeException("Filiere introuvable");
		return fi;
	}
	@Override
	public Filiere getFiliere(int id_filiere) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
    public List<Filiere> getFilieresPaginated(int pageSize, int offset) {
        Query query = em.createQuery("SELECT f FROM Filiere f");
        query.setFirstResult(offset);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }
	@Override
    // Méthode pour obtenir le nombre total de filières
    public int getTotalRecords() {
        Query query = em.createQuery("SELECT COUNT(f) FROM Filiere f");
        return ((Long) query.getSingleResult()).intValue();
    }
	@Override
	public List<Filiere> searchFiliereByName(String name) {
	    // Par exemple : utilisation de JPQL pour rechercher les filières
	    return em.createQuery("SELECT f FROM Filiere f WHERE f.nom_filiere LIKE :name", Filiere.class)
	             .setParameter("name", "%" + name + "%") // Recherche partielle
	             .getResultList();
	}
	
}
