package metier;

import java.util.List;

import javax.ejb.Local;

import metier.entities.Categorie;
import metier.entities.Filiere;
import metier.entities.Matiere;
import metier.entities.User;
@Local
public interface MatiereLocal {
	
	public Matiere getMatiere(int id_matiere);
	public List<Matiere> listMatiere();
	public void removeMatiere(int id_matiere);
}
