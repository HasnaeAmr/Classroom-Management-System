 package metier;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import metier.entities.Filiere;


@Stateless(name="Filiere")
 @Local(FiliereLocal.class)
 @Remote(FiliereRemote.class)
public class FiliereEJBImp implements FiliereLocal, FiliereRemote {
	@PersistenceContext(unitName = "SallesEJB")
	private EntityManager em;
	public FiliereEJBImp() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public Filiere AddFiliere(Filiere fi) {
		em.persist(fi);
		return fi;
	}
	@Override
	public Filiere getFiliere(int id_filiere) {
		Filiere fi=em.find(Filiere.class, id_filiere);
		if (fi==null) throw new RuntimeException("Filiere introuvable");
		return fi;
	}
	@Override
	public List<Filiere> listFiliere(){
		Query req=em.createQuery("select f from Filiere f");
		return req.getResultList();
	}
	@Override
	public void setFiliere(int id_filiere,String nom_filiere,String effectif) {
		Filiere fi=em.find(Filiere.class, id_filiere);
		fi.setId_filiere(id_filiere);
		fi.setNom_filiere(nom_filiere);
		fi.setEffectif(effectif);
	}
	@Override
	public void removeFiliere(int id_filiere) {
		Filiere fi=em.find(Filiere.class, id_filiere);
		em.remove(fi);
		// test:
		System.out.println("deleted");
	}
}
