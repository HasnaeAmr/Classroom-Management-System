package metier;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import metier.entities.Journalisation;

public class JournalisationEJBImp implements JournalisationLocal, JournalisationRemote{

	@PersistenceContext(unitName = "SallesEJB")
    private EntityManager em;
	
	public List<Journalisation> listJournalisation(){
		Query req = em.createQuery("select j from Journalisation j");
        return req.getResultList();
	}
	
	public void journaliser(String description) {
		Journalisation j = new Journalisation(description);
		em.merge(j);
	}
}
