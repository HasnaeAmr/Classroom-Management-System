package metier.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
@Entity
@Table(name="matiere_filiere_categorie")
public class MatiereFiliereCategorie {

	 @EmbeddedId
	    private MatiereFiliereCategorieId id;

	    @ManyToOne
	    @MapsId("id_matiere")
	    @JoinColumn(name = "id_matiere")
	    private Matiere matiere;


		@ManyToOne
	    @MapsId("id_filiere")
	    @JoinColumn(name = "id_filiere")
	    private Filiere filiere;

	    @ManyToOne
	    @MapsId("id_categorie")
	    @JoinColumn(name = "id_categorie")
	    private Categorie categorie;

	    private int nbr_heure;

	    @ManyToOne
	    @JoinColumn(name = "id_prof")
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
