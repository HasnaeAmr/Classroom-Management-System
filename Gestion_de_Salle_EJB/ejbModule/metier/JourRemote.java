package metier;

import java.util.List;

import javax.ejb.Remote;

import metier.entities.Jour;

@Remote
public interface JourRemote {

	public List<Jour> listJour();
}
