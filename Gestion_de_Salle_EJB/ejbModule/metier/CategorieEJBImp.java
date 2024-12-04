package metier;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import metier.entities.Categorie;


@Stateless(name="Categorie")
@Local(CategorieLocal.class)
@Remote(CategorieRemote.class)
public class CategorieEJBImp implements CategorieLocal,CategorieRemote {
	@PersistenceContext(unitName = "SallesEJB")
	private EntityManager em;
	public CategorieEJBImp() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Categorie getCategorie(int id_categorie) {
		Categorie cat=em.find(Categorie.class, id_categorie);
		if (cat==null) throw new RuntimeException("Categorie introuvable");
		return cat;
	}
	@Override
	public List<Categorie> listCategorie(){
		Query req=em.createQuery("select c from Categorie c");
		return req.getResultList();
	}
	@Override
	public void setCategorie(int id_categorie,String type_categorie) {
		Categorie cat=em.find(Categorie.class, id_categorie);
		cat.setType_categorie(type_categorie);
	}
	@Override
	public void removeCategorie(int id_categorie) {
		Categorie cat=em.find(Categorie.class, id_categorie);
		em.remove(cat);
		// test:
		System.out.println("deleted");
	}

}
