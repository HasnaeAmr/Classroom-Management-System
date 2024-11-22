package metier;

<<<<<<< HEAD
import java.util.List;

import javax.ejb.Remote;

import metier.entities.Horaire;
import metier.entities.Jour;
import metier.entities.Salle;
@Remote
public interface SalleRemote {
	public List<Salle> listSalles();
	public Salle getSalle(Long id);
	public Salle ajouterSalle(Salle s);
	public void modifierSalle(Long id, Salle s);
	public void supprimerSalle(Long id);
	public List<Salle> filtreJH(Horaire h, Jour j);
	public List<Salle> filtreJ(Jour j);
	public List<Salle> filtreH(Horaire h);
	public boolean getEtat(Long id, Horaire h, Jour j);
	public void setEtat(Long id, Horaire h, Jour j , boolean e);
	public List<Salle> getSallesVides();
}
=======


public interface SalleRemote {
	
}
>>>>>>> c37795ed34f928c3e0327e8e34f625867590da9a
