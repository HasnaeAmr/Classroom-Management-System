package metier.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="reservation")
public class Reservation implements Serializable {
	


	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_reservation;
	private int duree_reservation;
	private String Etat_reservation;
	

	public Reservation() {
		// TODO Auto-generated constructor stub
	}
	public int getId_reservation() {
		return id_reservation;
	}


	public void setId_reservation(int id_reservation) {
		this.id_reservation = id_reservation;
	}


	public int getDuree_reservation() {
		return duree_reservation;
	}


	public void setDuree_reservation(int duree_reservation) {
		this.duree_reservation = duree_reservation;
	}
	public String getEtat_reservation() {
		return Etat_reservation;
	}
	public void setEtat_reservation(String etat_reservation) {
		Etat_reservation = etat_reservation;
	}
	
	 @ManyToOne
	    @JoinColumn(name = "id_salle")
	    private Salle salle;
	    public Salle getSalle() {return salle;}
	    public void setSalle(Salle salle) {this.salle = salle;}
	 
	 @ManyToOne
	    @JoinColumn(name = "id_prof")
	    private User prof;
	 	public User getProf() {return prof;}
	    public void setProf(User prof) {this.prof = prof;}

	

}
