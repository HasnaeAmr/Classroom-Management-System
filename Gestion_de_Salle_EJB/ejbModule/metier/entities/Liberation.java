package metier.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="liberation")
public class Liberation implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_liberation;
	@ManyToOne
	@JoinColumn(name="id_prof")
	private User prof;
	@ManyToOne
	@JoinColumn(name="id_salle")
	private Salle salle;
	@Column(name="duree_liberation")
	private int duree_liberation;
	@Column(name="type_liberation")
	private boolean type_liberation; // true = normal
	
	public Liberation(Salle salle, User prof, int duree_liberation, boolean type_liberation) {
		this.salle=salle;
		this.prof=prof;
		this.duree_liberation=duree_liberation;
		this.type_liberation=type_liberation;
	}
	public Salle getSalle() {
		return salle;
	}
	public int getDureeLiberation() {
		return duree_liberation;
	}
	public boolean getTypeLiberation() {
		return type_liberation;
	}

}
