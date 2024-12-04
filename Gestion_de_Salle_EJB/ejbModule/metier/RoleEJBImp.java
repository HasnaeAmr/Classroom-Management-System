package metier;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import metier.entities.Role;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;



@Stateless
@Local(RoleLocal.class)
public class RoleEJBImp implements RoleRemote, RoleLocal {

	@PersistenceContext(unitName = "SallesEJB")
    private EntityManager em;
	

	public List<Role> listRoles(){

		Query req = em.createQuery("select r from Role r");

        return req.getResultList();
	}
	public Role ajouterRole(Role r) {
		return em.merge(r);
	}
	public void getRole(Long id) {
		
	}
	
	public void supprimerRole(Long id) {
		Role r = em.find(Role.class, id);
		if(r!=null)
			em.remove(r);
	}

}
