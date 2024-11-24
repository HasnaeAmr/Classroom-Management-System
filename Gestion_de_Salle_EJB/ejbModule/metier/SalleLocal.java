package metier;

import java.util.List;

import javax.ejb.Local;

import metier.entities.Horaire;
import metier.entities.Jour;
import metier.entities.Salle;
@Local
public interface SalleLocal {
	public List<Salle> listSalles();
	public Salle getSalle(Long id);
	public Salle ajouterSalle(Salle s);
	public void modifierSalle(Long id, Salle s);
	public void supprimerSalle(Long id);
	public List<Salle> filtreJH(Horaire h, Jour j);
	public List<Salle> filtreJ( Jour j);
	public List<Salle> filtreH(Horaire h);
	public List<Salle> filtreP(Long id);
	public boolean getEtat(Long id, Horaire h, Jour j);
	public void setEtat(Long id, Horaire h, Jour j , boolean e);
	public List<Salle> getSallesVides();
}
