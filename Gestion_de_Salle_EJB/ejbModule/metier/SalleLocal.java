package metier;

import java.util.List;

import javax.ejb.Local;

import metier.entities.Categorie;
import metier.entities.EtatSalle;
import metier.entities.Filiere;
import metier.entities.Horaire;
import metier.entities.Jour;
import metier.entities.MatiereFiliereCategorie;
import metier.entities.Salle;
import metier.entities.User;
@Local
public interface SalleLocal {
	public List<Salle> listSalles();
	public Salle getSalle(Long id);
	public Salle ajouterSalle(Salle s);
	public void modifierSalle(Long id, Salle s);
	public List<EtatSalle> filtreEtatNom(String nom);
	public List<Salle> getSallesByCategorie(int id_categorie);
	public void supprimerSalle(Long id);
	public List<EtatSalle> filtreJH(Horaire h, Jour j);
	public List<EtatSalle> filtreJ(Jour j);
	public List<EtatSalle> filtreH(Horaire h);
	public List<EtatSalle> filtreP(int id);
	public EtatSalle getEtatById(Long id);
	public List<EtatSalle> filtrePJH(Long id, Horaire h, Jour j);
	public List<EtatSalle> filtrePJ(Long id, Jour j);
	public List<EtatSalle> filtrePH(Long id, Horaire h);
	public boolean getEtat(Long id, Horaire h, Jour j);
	public void setEtat(Long id, boolean e, MatiereFiliereCategorie mat);
	public void setProf(Long idEtat, User prof);
	public List<EtatSalle> getSallesVides();
	public List<EtatSalle> getEtatSalles();
	public List<Salle> getSalleByName(String nomSalle);
	public void setMFC(Long id,MatiereFiliereCategorie m);
	public void calculChargeHor();
	public void setEtat(Long id, Horaire h, Jour j, boolean e, MatiereFiliereCategorie mfc);
	public List<EtatSalle> getSallesVidesByHoraireNDJourAndCat(Categorie cat, Horaire h, Jour j);
	public List<EtatSalle> getLesSallesVidesByCategorie(Categorie cat);
}
