package metier;

import java.util.List;

import javax.ejb.Remote;


import metier.entities.Matiere;


@Remote
public interface MatiereRemote {
	
	public Matiere getMatiere(int id_matiere);
	public List<Matiere> listMatiere();
	public void removeMatiere(int id_matiere);
}
