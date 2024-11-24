package metier;

import javax.ejb.Local;

import metier.entities.Horaire;
import metier.entities.Jour;
import metier.entities.Salle;
import metier.entities.User;

@Local
public interface LiberationLocal {
	public void libererartionDefinitive(Salle salle, Horaire horaire, Jour jour, User prof);
	public void libererationExp(Salle salle, Horaire horaire, Jour jour, User prof, int duree);
}
