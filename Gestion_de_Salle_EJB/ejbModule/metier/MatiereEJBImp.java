package metier;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import metier.entities.Categorie;
import metier.entities.Filiere;
import metier.entities.Matiere;
import metier.entities.User;

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
	public Matiere AddMatiere(Matiere mat) {
		em.persist(mat);
		return mat;
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
	public void setMatiere(int id_matiere,String nom_matiere,Categorie cat,Filiere fi,User user) {
		Matiere mat=em.find(Matiere.class, id_matiere);
		mat.setNom_matiere(nom_matiere);
		mat.setCategorie(cat);
		mat.setFiliere(fi);
		mat.setProf(user);
	}
	@Override
	public void removeMatiere(int id_matiere) {
		Matiere mat=em.find(Matiere.class, id_matiere);
		em.remove(mat);
		// test:
		System.out.println("deleted");
	}
}
