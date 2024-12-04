package metier.entities;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="filiere")
public class Filiere {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_filiere;
	private String nom_filiere;
	private String effectif;
	
	 


	
	public Filiere() {
		// TODO Auto-generated constructor stub
	}
	public Filiere(String nom_filiere, String effectif) {
		super();
		this.nom_filiere = nom_filiere;
		this.effectif = effectif;
	}
	
	public int getId_filiere() {
		return id_filiere;
	}

	public void setId_filiere(int id_filiere) {
		this.id_filiere = id_filiere;
	}

	public String getNom_filiere() {
		return nom_filiere;
	}

	public void setNom_filiere(String nom_filiere) {
		this.nom_filiere = nom_filiere;
	}
	public String getEffectif() {
		return effectif;
	}
	public void setEffectif(String effectif) {
		this.effectif = effectif;
	}

}
