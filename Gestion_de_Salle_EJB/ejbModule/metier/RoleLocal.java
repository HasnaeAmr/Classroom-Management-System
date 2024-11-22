package metier;

import java.util.List;

import javax.ejb.Local;
import javax.management.relation.Role;

@Local
public interface RoleLocal {

	public List<Role> lisrRoles();
	public Role ajouterRole(Role role);
	public void getRole(Long id);
	public void supprimerRole(Long id);
}
