package metier;

import java.util.List;

import javax.ejb.Remote;

import metier.entities.Horaire;

@Remote
public interface HoraireRemote {
	public List<Horaire> listHoraire();

}
