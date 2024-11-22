package metier.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "salle")
public class Salle implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_salle")
	private Long id_salle;
	@Column(name="nom_salle")
	private String nom_salle;
	@Column(name="capacite")
	private int capacite;
	@OneToMany(mappedBy = "id_salle")
    private List<EtatSalle> etatsSalle;
	
	public Salle(String nom, int capacite, boolean etat, Long id_horaire, Long id_jour) {
		this.nom_salle=nom;
		this.capacite=capacite;
	}
	

	public Long getIdSalle() {
        return id_salle;
    }

    public void setIdSalle(Long id_salle) {
        this.id_salle = id_salle;
    }

    public String getNomSalle() {
        return nom_salle;
    }

    public void setNomSalle(String nom_salle) {
        this.nom_salle = nom_salle;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }
    
}
