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
@Table(name="matiere")
public class Matiere implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_matiere;
	private String nom_matiere;
	
	
	public Matiere() {
		// TODO Auto-generated constructor stub
	}

	public Matiere(int id_matiere, String nom_matiere,Categorie cat,Filiere fi,User user) {
		super();
		this.id_matiere = id_matiere;
		this.nom_matiere = nom_matiere;
		this.Cat=cat;
		this.fi=fi;
		this.user=user;
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
	
	 @ManyToOne
	 @JoinColumn(name="id_categorie")
	 private Categorie Cat;
	 public Categorie getCategorie() {return Cat;}
	 public void setCategorie(Categorie Cat) {this.Cat = Cat;}
	 
	 @ManyToOne
	 @JoinColumn(name="id_filiere")
	 private Filiere fi;
	 public Filiere getFiliere() {return fi;}
	 public void setFiliere(Filiere fi) {this.fi = fi;}
	 
	 @ManyToOne
	 @JoinColumn(name="id")
	 private User user;
	 public User getProf() {return user;}
	 public void setProf(User user) {this.user = user;}
	 

}
