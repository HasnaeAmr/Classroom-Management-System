package metier;

import java.util.List;

import javax.ejb.Remote;

import metier.entities.Categorie;

@Remote
public interface CategorieRemote {
	
	public Categorie getCategorie(int id_categorie);
	public List<Categorie> listCategorie();
<<<<<<< HEAD
	public void setCategorie(int id_categorie,String type_categorie);
=======
>>>>>>> d3934161c145de4dacb56a9c8c17b1aef201af01
	public void removeCategorie(int id_categorie);
}
