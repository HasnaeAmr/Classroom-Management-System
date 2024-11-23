package metier;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import metier.entities.Journalisation;

public class JournalisationEJBImp implements JournalisationLocal, JournalisationRemote{

	@PersistenceContext(unitName = "SallesEJB")
    	private EntityManager em;
	
	public void journaliser(String description) {
		Journalisation j = new Journalisation(description);
		em.merge(j);
	}
}
