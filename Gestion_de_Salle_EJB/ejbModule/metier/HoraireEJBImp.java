package metier;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import metier.entities.Horaire;
import metier.entities.Salle;

public class HoraireEJBImp {

	public HoraireEJBImp() {
		// TODO Auto-generated constructor stub
	}
	@PersistenceContext(unitName = "SallesEJB")
    private EntityManager em;
	

	public List<Horaire> listHoraire(){
		Query req = em.createQuery("select h from Horaire h");
        return req.getResultList();
	}
}
