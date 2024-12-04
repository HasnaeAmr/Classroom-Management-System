package metier;

import java.util.List;

import javax.ejb.Local;
import metier.entities.Role;

@Local
public interface RoleLocal {

	public List<Role> listRoles();
	public Role ajouterRole(Role role);
	public void getRole(Long id);
	public void supprimerRole(Long id);
}
