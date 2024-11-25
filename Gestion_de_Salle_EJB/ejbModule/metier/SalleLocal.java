package metier;

import java.util.List;

import javax.ejb.Local;

import metier.entities.Categorie;
import metier.entities.EtatSalle;
import metier.entities.Horaire;
import metier.entities.Jour;
import metier.entities.Salle;
@Local
public interface SalleLocal {
	public List<Salle> listSalles();
	public Salle getSalle(Long id);
	public Salle ajouterSalle(Salle s);
	public void modifierSalle(Long id, Salle s);
	public EtatSalle filtreEtatNom(String nom);
	public void supprimerSalle(Long id);
	public List<EtatSalle> filtreJH(Horaire h, Jour j);
	public List<EtatSalle> filtreJ(Jour j);
	public List<EtatSalle> filtreH(Horaire h);
	public List<EtatSalle> filtreP(int id);
	public List<EtatSalle> filtrePJH(Long id, Horaire h, Jour j);
	public List<EtatSalle> filtrePJ(Long id, Jour j);
	public List<EtatSalle> filtrePH(Long id, Horaire h);
	public boolean getEtat(Long id, Horaire h, Jour j);
	public void setEtat(Long id, Horaire h, Jour j , boolean e);
	public List<EtatSalle> getSallesVides();
	public List<EtatSalle> getEtatSalles();
}
