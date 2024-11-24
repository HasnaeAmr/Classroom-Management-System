package metier;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import metier.entities.Role;
import metier.entities.User;

@Stateless(name="User")
@Local(UserLocal.class)
@Remote(UserRemote.class)
public class UserEJBImp  implements UserLocal,UserRemote{
	
	@PersistenceContext(unitName = "SallesEJB")
	private EntityManager em;
	public UserEJBImp() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public User getUser(int id) {
		User user=em.find(User.class,id);
		if (user==null) throw new RuntimeException("User introuvable");
		return user;
	}
	@Override
	public List<User> listUser(){
		Query req=em.createQuery("select u from User u");
		return req.getResultList();
		}
	@Override
	public void setUser(int id,String nom,String mdp,Role role) {
		User user=em.find(User.class, id);
		user.setId(id);
		user.setNom(nom);
		user.setMdp(mdp);
		user.setRole(role);
	}
	
}
