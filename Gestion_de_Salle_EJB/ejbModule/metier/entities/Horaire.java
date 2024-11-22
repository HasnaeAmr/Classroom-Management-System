package metier.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="horaire")
public class Horaire implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_horaire;
	@Column(name="nom_horaire")
	private String nom_horaire;
	@OneToMany(mappedBy = "id_horaire")
    private List<EtatSalle> etatsSalle;
	
	
	public Horaire(String nom_horaire) {
		super();
		this.nom_horaire=nom_horaire;
	}
	public Long getIdHoraire() {
		return id_horaire;
	}
	public String getNomHoraire() {
		return nom_horaire;
	}
	public void setNomHoraire(String nom) {
		nom_horaire=nom;
	}
}
