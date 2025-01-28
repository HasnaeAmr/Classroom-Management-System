package metier;

import java.util.List;
import metier.entities.Notification;
import metier.entities.User;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import metier.entities.User;

@Stateless
@Local(NotificationLocal.class)
@Remote(NotificationRemote.class)
public class NotificationEJBImpl implements NotificationLocal, NotificationRemote{
	
	@PersistenceContext(unitName = "SallesEJB")
	private EntityManager em;
	
	public NotificationEJBImpl() {}
	
	public List<Notification> getNotifs(User u){
		return em.createQuery("select n from Notification n where n.user=:u").setParameter("u", u).getResultList();
	}
	public void supprimerNotification(Long id_notif) {
		Notification notif = em.find(Notification.class, id_notif);
		em.remove(notif);
	}
	public void supprimerTous(User user) {
		Query q = em.createQuery("DELETE FROM Notification nu WHERE nu.user=:u");
		q.setParameter("u", user);
		q.executeUpdate();
	}

	public void notifier(User user, String description) {
		System.out.println("creating notif...");
		Notification notif = new Notification(description, user) ;
		em.persist(notif);
	    System.out.println("notif created ! "+description);  
	}

	@Override
	public void supprimerTous(Long id_notif) {
		// TODO Auto-generated method stub
		
	}

}
