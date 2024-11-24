package metier;

import java.util.List;

import javax.ejb.Remote;

import metier.entities.Journalisation;

@Remote
public interface JournalisationRemote {
	public List<Journalisation> listJournalisation();
	public void journaliser(String description);
}
