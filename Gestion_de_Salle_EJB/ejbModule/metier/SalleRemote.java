package metier;

import java.util.List;

import javax.ejb.Remote;

import metier.entities.Role;
import metier.entities.User;

@Remote
public interface SalleRemote {
	public User AddUser(User user);
	public User getUser(int id);
	public List<User> listUser();
	public void setUser(int id,String nom,String mdp,Role role);
	public void removeUser(int id);
}
