package metier;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import metier.entities.Matiere;


@Stateless(name="Matiere")
@Local(MatiereLocal.class)
@Remote(MatiereRemote.class)
public class MatiereEJBImp implements MatiereLocal,MatiereRemote{
	@PersistenceContext(unitName = "SallesEJB")
	private EntityManager em;
	public MatiereEJBImp() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Matiere getMatiere(int id_matiere) {
		Matiere mat=em.find(Matiere.class, id_matiere);
		if (mat==null) throw new RuntimeException("Matiere introuvable");
		return mat;
	}
	@Override
	public List<Matiere> listMatiere(){
		Query req=em.createQuery("select m from Matiere m");
		return req.getResultList();
	}
	
	@Override
	public void removeMatiere(int id_matiere) {
		Matiere mat=em.find(Matiere.class, id_matiere);
		em.remove(mat);
		// test:
		System.out.println("deleted");
	}
}
