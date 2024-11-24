package metier;

import java.util.List;

import javax.ejb.Local;

import metier.entities.Reservation;
import metier.entities.Salle;
@Local
public interface ReservationLocal {
	public Reservation getReservation(int id_reservation);
	public void setReservation(int id_reservation,int duree_reservaiton,Long id_salle);
	public List<Reservation> listReservation();
	public void AnnulerReservation(int id_reservation);
	public String Reserver(Long id_prof, Long id_salle, Long id_horaire, Long id_jour,int duree_reservation);
	public List<Salle> ProposerAlternatives(Long id_horaire,Long id_jour);
	public void MettreEnAttente(Long id_prof,Long id_salle,Long id_horaire,Long id_jour,int duree_reservation);
	public List<Reservation> getReservationsEnAttente(Long id_salle) ;
	public void RendreApproved(Long id_salle,Long id_horaire,Long id_jour);
}
