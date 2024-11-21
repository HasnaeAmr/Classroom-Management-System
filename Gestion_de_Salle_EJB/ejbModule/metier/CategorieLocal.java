package metier;

import java.util.List;

import javax.ejb.Local;

import metier.entities.Categorie;

@Local
public interface CategorieLocal {
	public Categorie AddCategorie(Categorie Cat);
	public Categorie getCategorie(int id_categorie);
	public List<Categorie> listCategorie();
	public void setCategorie(int id_categorie,String type_categorie,int nombre_heures);
	public void removeCategorie(int id_categorie);
}
