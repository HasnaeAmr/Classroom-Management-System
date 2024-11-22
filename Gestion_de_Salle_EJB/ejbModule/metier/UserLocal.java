package metier;

import java.util.List;

import javax.ejb.Local;

import metier.entities.Role;
import metier.entities.User;

@Local
public interface UserLocal {
	public User AddUser(User user);
	public User getUser(int id);
	public List<User> listUser();
	public void setUser(int id,String nom,String mdp,Role role);
	public void removeUser(int id);
}
