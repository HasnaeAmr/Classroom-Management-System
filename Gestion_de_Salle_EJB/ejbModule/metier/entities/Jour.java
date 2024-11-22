package metier.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

public class Jour implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_jour;
	@Column(name="nom_jour")
	private String nom_jour;
	@OneToMany(mappedBy = "id_horaire")
    private List<EtatSalle> etatsSalle;
	
	public Jour(String nom_jour) {
		super();
		this.nom_jour=nom_jour;
	}
	public Long getIdJour() {
		return id_jour;
	}
	public String getNomJour() {
		return nom_jour;
	}
	public void setNomJour(String nom) {
		nom_jour=nom;
	}
	
}
