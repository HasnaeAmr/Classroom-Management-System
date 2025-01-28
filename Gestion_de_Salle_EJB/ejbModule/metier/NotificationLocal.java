package metier;

import java.util.List;

import javax.ejb.Local;
import javax.management.Notification;

import metier.entities.User;

@Local
public interface NotificationLocal {
	public List<metier.entities.Notification> getNotifs(User u);
	public void supprimerNotification(Long id_notif);
	public void supprimerTous(Long id_notif);
	public void notifier(User user, String description);
}
