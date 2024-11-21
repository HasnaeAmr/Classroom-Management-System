package metier;

import java.util.List;

import javax.ejb.Local;
import metier.entities.Filiere;


@Local
public interface FiliereLocal {
	public Filiere AddFiliere(Filiere fi);
	public Filiere getFiliere(int id_filiere);
	public List<Filiere> listFiliere();
	public void setFiliere(int id_filiere,String nom_filiere,String effectif);
	public void removeFiliere(int id_filiere);
}
