package metier;

import java.util.List;

import javax.ejb.Local;


import metier.entities.Matiere;

@Local
public interface MatiereLocal {
	
	public Matiere getMatiere(int id_matiere);
	public List<Matiere> listMatiere();
	public void removeMatiere(int id_matiere);
}
