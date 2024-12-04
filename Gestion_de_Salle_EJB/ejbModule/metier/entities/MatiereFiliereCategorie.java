package metier.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name="matiere_filiere_categorie")
public class MatiereFiliereCategorie {

	 public MatiereFiliereCategorie() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MatiereFiliereCategorie(Matiere matiere, Filiere filiere, Categorie categorie, int nbr_heure, User prof) {
		super();
		this.matiere = matiere;
		this.filiere = filiere;
		this.categorie = categorie;
		this.nbr_heure = nbr_heure;
		this.prof = prof;
	}

	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int IDmfc;



		public int getIDmfc() {
		return IDmfc;
	}

	public void setIDmfc(int iDmfc) {
		IDmfc = iDmfc;
	}

		@ManyToOne
	    @JoinColumn(name = "id_matiere",nullable = false)
	    private Matiere matiere;


		@ManyToOne
	    @JoinColumn(name = "id_filiere",nullable = false)
	    private Filiere filiere;

	    @ManyToOne
	    @JoinColumn(name = "id_categorie",nullable = false)
	    private Categorie categorie;

	    private int nbr_heure;

	    @ManyToOne
	    @JoinColumn(name = "id_prof",nullable = false)
	    private User prof;

	    public Matiere getMatiere() {
			return matiere;
		}

		public void setMatiere(Matiere matiere) {
			this.matiere = matiere;
		}

		public Filiere getFiliere() {
			return filiere;
		}

		public void setFiliere(Filiere filiere) {
			this.filiere = filiere;
		}

		public Categorie getCategorie() {
			return categorie;
		}

		public void setCategorie(Categorie categorie) {
			this.categorie = categorie;
		}

		public int getNbr_heure() {
			return nbr_heure;
		}

		public void setNbr_heure(int nbr_heure) {
			this.nbr_heure = nbr_heure;
		}

		public User getProf() {
			return prof;
		}

		public void setProf(User prof) {
			this.prof = prof;
		}

}
