package metier;

import javax.ejb.Remote;

import metier.entities.Horaire;
import metier.entities.Jour;
import metier.entities.Salle;
import metier.entities.User;

@Remote
public interface LiberationRemote {
	public void libererartionDefinitive(Salle salle, Horaire horaire, Jour jour, User prof);
	public void libererationExp(Salle salle, Horaire horaire, Jour jour, User prof, int duree);
}
