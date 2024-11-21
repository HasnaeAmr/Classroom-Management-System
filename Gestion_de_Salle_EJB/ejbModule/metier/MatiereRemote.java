package metier;

import java.util.List;

import javax.ejb.Remote;

import metier.entities.Categorie;
import metier.entities.Filiere;
import metier.entities.Matiere;
import metier.entities.User;

@Remote
public interface MatiereRemote {
	public Matiere AddMatiere(Matiere mat);
	public Matiere getMatiere(int id_matiere);
	public List<Matiere> listMatiere();
	public void setMatiere(int id_matiere,String nom_matiere,Categorie cat,Filiere fi,User user);
	public void removeMatiere(int id_matiere);
}
