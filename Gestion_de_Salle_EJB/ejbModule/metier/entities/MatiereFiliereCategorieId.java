package metier.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;
@Embeddable
public class MatiereFiliereCategorieId implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Long id_matiere;
	private Long id_filiere;
	private Long id_categorie;
	public MatiereFiliereCategorieId() {
		// TODO Auto-generated constructor stub
	}
	 public Long getId_matiere() {
			return id_matiere;
		}
		public void setId_matiere(Long id_matiere) {
			this.id_matiere = id_matiere;
		}
		public Long getId_filiere() {
			return id_filiere;
		}
		public void setId_filiere(Long id_filiere) {
			this.id_filiere = id_filiere;
		}
		public Long getId_categorie() {
			return id_categorie;
		}
		public void setId_categorie(Long id_categorie) {
			this.id_categorie = id_categorie;
		}
}
