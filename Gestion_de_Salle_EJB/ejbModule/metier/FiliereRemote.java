package metier;

import java.util.List;

import javax.ejb.Remote;

import metier.entities.Filiere;

@Remote
public interface FiliereRemote {
	public Filiere AddFiliere(Filiere fi);
	public Filiere getFiliere(int id_filiere);
	public List<Filiere> listFiliere();
	public void setFiliere(int id_filiere,String nom_filiere,String effectif);
	public void removeFiliere(int id_filiere);
}
