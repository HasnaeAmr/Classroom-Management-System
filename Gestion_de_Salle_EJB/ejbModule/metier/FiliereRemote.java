package metier;

import java.util.List;

import javax.ejb.Remote;

import metier.entities.Filiere;

@Remote
public interface FiliereRemote {
	public Filiere AddFiliere(Filiere fi);
	public Filiere getFiliere(String Nom_filiere);
	public List<Filiere> listFiliere();
	public void setFiliere(int id_filiere,String nom_filiere,String effectif);
	public void removeFiliere(int id_filiere);
	Filiere getFiliereById(int id_filiere);
	List<Filiere> getFilieresPaginated(int pageSize, int offset);
	int getTotalRecords();
	List<Filiere> searchFiliereByName(String name);
}
