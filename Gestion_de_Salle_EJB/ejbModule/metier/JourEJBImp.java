package metier;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


import metier.entities.Jour;

public class JourEJBImp implements JourLocal {

	public JourEJBImp() {
		// TODO Auto-generated constructor stub
	}
	@PersistenceContext(unitName = "SallesEJB")
    private EntityManager em;
	

	public List<Jour> listJour(){
		Query req = em.createQuery("select j from Jour j");
        return req.getResultList();
	}
	public Jour getJour(Long id_jour) {
		Jour j=em.find(Jour.class, id_jour);
		return j;
	}
}
