package metier;

import java.util.List;

import javax.ejb.Local;

import metier.entities.Horaire;

@Local
public interface HoraireLocal {
	public List<Horaire> listHoraire();
}
