package metier.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="categorie")
public class Categorie implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_categorie;
	private String type_categorie;
	private int nombre_heures;

	public Categorie() {
		// TODO Auto-generated constructor stub
	}
	public Categorie(int id_categorie, String type_categorie, int nombre_heures) {
		super();
		this.id_categorie = id_categorie;
		this.type_categorie = type_categorie;
		this.nombre_heures = nombre_heures;
	}
	public int getId_categorie() {
		return id_categorie;
	}

	public void setId_categorie(int id_categorie) {
		this.id_categorie = id_categorie;
	}

	public String getType_categorie() {
		return type_categorie;
	}

	public void setType_categorie(String type_categorie) {
		this.type_categorie = type_categorie;
	}

	public int getNombre_heures() {
		return nombre_heures;
	}

	public void setNombre_heures(int nombre_heures) {
		this.nombre_heures = nombre_heures;
	}

}
