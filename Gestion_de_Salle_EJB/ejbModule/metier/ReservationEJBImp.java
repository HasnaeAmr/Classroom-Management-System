package metier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import metier.entities.Horaire;
import metier.entities.Jour;
import metier.entities.Reservation;
import metier.entities.Salle;
import metier.entities.User;
@Stateless(name="Reservation")
@Local(ReservationLocal.class)
@Remote(ReservationRemote.class)
public class ReservationEJBImp implements ReservationLocal,ReservationRemote{
	@PersistenceContext(unitName = "SallesEJB")
	private EntityManager em;
	@EJB
	SalleEJBImp salleejb;
	//Map<Long,List<Reservation>> demandes=new HashMap<>();
	public ReservationEJBImp() {
		
	}
	
	@Override
	public Reservation getReservation(int id_reservation) {
		Reservation rsv=em.find(Reservation.class, id_reservation);
		if (rsv==null) throw new RuntimeException("Categorie introuvable");
		return rsv;
	}
	@Override
	public void setReservation(int id_reservation,int duree_reservaiton,Long id_salle) {
		Reservation rsv=em.find(Reservation.class, id_reservation);
		rsv.setDuree_reservation(duree_reservaiton);
		rsv.setSalle(em.find(Salle.class, id_salle));
		
	}
	@Override
	public List<Reservation> listReservation(){
		Query req=em.createQuery("select r from Reservation r");
		return req.getResultList();
	}
	@Override
	public void AnnulerReservation(int id_reservation) {
		Reservation rsv=em.find(Reservation.class, id_reservation);
		em.remove(rsv);
		// test:
		System.out.println("Annulée (deleted)");
	}
	@Override
	public String Reserver(Long id_prof,Long id_salle,Long id_horaire,Long id_jour,int duree_reservation) {
		
		//recuperer les info:
		
		Salle salle = em.find(Salle.class, id_salle);
        User prof = em.find(User.class, id_prof);
        Horaire horaire = em.find(Horaire.class, id_horaire);
        Jour jour = em.find(Jour.class, id_jour);
        
       // checker if la salle est vide :
		if(salleejb.getEtat(id_salle,horaire, jour)) {
			//creer une reservation de type "approved" et la restorer:
			Reservation rsv=new Reservation();
			rsv.setProf(prof);
			rsv.setSalle(salle);
			rsv.setEtat_reservation("Approved");
			rsv.setDuree_reservation(duree_reservation);
			em.persist(rsv);
			salleejb.setEtat(id_salle, horaire, jour, false);
			return "Reservation Confirmée !";
		}
		else {
			return "La Salle demandé n'est pas Libre !";
			}
		
	}
	@Override
	public List<Salle> ProposerAlternatives(Long id_horaire,Long id_jour) {
		 Horaire horaire = em.find(Horaire.class, id_horaire);
	     Jour jour = em.find(Jour.class, id_jour);
		if(salleejb.getSallesVidesByHoraireNDJour(horaire, jour).isEmpty()) {
			return salleejb.getSallesVides();
		}
		else {
			return salleejb.getSallesVidesByHoraireNDJour(horaire, jour);
		}
	}
	@Override
	public void MettreEnAttente(Long id_prof,Long id_salle,Long id_horaire,Long id_jour,int duree_reservation) {
		//recuperer les infos:
		Salle salle = em.find(Salle.class, id_salle);
        User prof = em.find(User.class, id_prof);
        Horaire horaire = em.find(Horaire.class, id_horaire);
        Jour jour = em.find(Jour.class, id_jour);
		
		//stocker la reservation sous le type "En attente";
		Reservation rsv=new Reservation();
		rsv.setProf(prof);
		rsv.setSalle(salle);
		rsv.setEtat_reservation("En_Attente");
		rsv.setDuree_reservation(duree_reservation);
		em.persist(rsv);
		
		 
	        
//		 if (!demandes.containsKey(id_salle)) {
//		        demandes.put(id_salle, new ArrayList<>()); 
//		    }
//		    demandes.get(id_salle).add(rsv); 
        	 
	}
	@Override
	public List<Reservation> getReservationsEnAttente(Long id_salle) {
	    
	   // if (!demandes.containsKey(id_salle)) {
	     
	        Query query = em.createQuery("SELECT r FROM Reservation r WHERE r.Salle.id = :id_salle AND r.etat_reservation = 'En_Attente'");
	        query.setParameter("id_salle", id_salle);
	        return query.getResultList();
	        
//	       /*List<Reservation> reservationsEnAttente = query.getResultList();*/
//	        if (!reservationsEnAttente.isEmpty()) {
//	            demandes.put(id_salle, reservationsEnAttente);	        }
//	        
//	    }
//
//	    return demandes.get(id_salle);
	}
	//Après la liberation de la salle:
	@Override
	public void RendreApproved(Long id_salle,Long id_horaire,Long id_jour) {
		
		  
        List<Reservation> reservationsEnAttente = getReservationsEnAttente(id_salle);
        if (!reservationsEnAttente.isEmpty()) {
            
            Reservation rsv = reservationsEnAttente.get(0);
            Reserver((long) rsv.getProf().getId(), id_salle, id_horaire, id_jour, rsv.getDuree_reservation());

            em.remove(rsv);
        }
    
		
//		List<Reservation> list=new ArrayList<>(getReservationsEnAttente(id_salle));
//		if (!list.isEmpty()) {
//		Reserver((long) list.get(0).getProf().getId(),id_salle,id_horaire,id_jour,list.get(0).getDuree_reservation());
//		
//		//Supprimer de la base de données la reservaition d'etat:"en attente" et la remplacer avec une nouvelle "Approved":
//		
//		em.remove(list.get(0));
//		
//		list.remove(0);
//		
//		//Mettre à jour Map:
//		demandes.put(id_salle, list);
//		}
	}
}
