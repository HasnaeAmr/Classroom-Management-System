package metier;

import java.util.List;

import metier.entities.Jour;

public interface JourLocal {

	public List<Jour> listJour();
	public Jour getJour(Long id_jour);
}
