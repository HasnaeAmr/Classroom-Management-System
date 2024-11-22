package metier;

import java.util.List;

import javax.ejb.Remote;

import metier.entities.Categorie;

@Remote
public interface CategorieRemote {
	public Categorie AddCategorie(Categorie Cat);
	public Categorie getCategorie(int id_categorie);
	public List<Categorie> listCategorie();
	public void setCategorie(int id_categorie,String type_categorie,int nombre_heures);
	public void removeCategorie(int id_categorie);
}
