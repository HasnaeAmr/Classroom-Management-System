package metier;

import java.util.List;

import javax.ejb.Local;

import metier.entities.Journalisation;

@Local
public interface JournalisationLocal {
	public List<Journalisation> listJournalisation();
	public void journaliser(String description);
}
