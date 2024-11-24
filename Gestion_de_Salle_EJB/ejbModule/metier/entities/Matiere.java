package metier.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="matiere")
public class Matiere implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_matiere;
	private String nom_matiere;
	
	@OneToMany(mappedBy = "matiere")
    private Set<MatiereFiliereCategorie> matiereFiliereCategories = new HashSet<>();


	

	public Matiere() {
		// TODO Auto-generated constructor stub
	}

	public Matiere(int id_matiere, String nom_matiere) {
		super();
		this.id_matiere = id_matiere;
		this.nom_matiere = nom_matiere;
		
	}
	public int getId_matiere() {
		return id_matiere;
	}


	public void setId_matiere(int id_matiere) {
		this.id_matiere = id_matiere;
	}


	public String getNom_matiere() {
		return nom_matiere;
	}


	public void setNom_matiere(String nom_matiere) {
		this.nom_matiere = nom_matiere;
	}
	
	

	public Set<MatiereFiliereCategorie> getMatiereFiliereCategories() {
			return matiereFiliereCategories;
		}

	public void setMatiereFiliereCategories(Set<MatiereFiliereCategorie> matiereFiliereCategories) {
			this.matiereFiliereCategories = matiereFiliereCategories;
		}
	
	 

}
