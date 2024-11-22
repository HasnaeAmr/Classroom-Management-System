package metier;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import metier.entities.Journalisation;

@Stateless(name="Journalisation")
@Local(JournalisationLocal.class)
@Remote(JournalisationRemote.class)
public class JournalisationEJBImp implements JournalisationLocal,JournalisationRemote{
	
	@PersistenceContext(unitName = "SallesEJB")
    private EntityManager em;
	
	public JournalisationEJBImp() {
		// TODO Auto-generated constructor stub
	}
	public List<Journalisation> listJournalisation(){
		Query req = em.createQuery("select j from Journalisation j");
        return req.getResultList();
	}
	public Journalisation addJournalisation(Journalisation journal) {
		em.persist(journal);
		return journal;
	}
}
