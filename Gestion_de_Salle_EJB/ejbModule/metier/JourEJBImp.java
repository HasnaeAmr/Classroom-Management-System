package metier;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import metier.entities.Horaire;

public class JourEJBImp {

	public JourEJBImp() {
		// TODO Auto-generated constructor stub
	}
	@PersistenceContext(unitName = "SallesEJB")
    private EntityManager em;
	

	public List<Horaire> listJour(){
		Query req = em.createQuery("select j from Jour j");
        return req.getResultList();
	}
}
