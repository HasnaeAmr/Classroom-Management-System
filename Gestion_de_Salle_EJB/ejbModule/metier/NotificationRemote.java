package metier;

import java.util.List;

import javax.ejb.Remote;
import javax.management.Notification;

import metier.entities.User;
@Remote
public interface NotificationRemote {
	public void supprimerNotification(Long id_notif);
	public void supprimerTous(User user);
	public void notifier(User user, String description);
}
