package metier;

import java.util.List;

import javax.ejb.Remote;
import javax.management.relation.Role;

@Remote
public interface RoleRemote {
	public List<Role> listRoles();
	public Role ajouterRole(Role role);
	public void getRole(Long id);
	public void supprimerRole(Long id);
}