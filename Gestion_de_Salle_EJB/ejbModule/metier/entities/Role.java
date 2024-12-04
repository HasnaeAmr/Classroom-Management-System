package metier.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Table(name="role")
public class Role implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	
	/*
	 * @OneToMany(mappedBy = "role") private List<User> utilisateurs;
	 */
	 
	 
	

	@Column(name="nom")
	String nom_role;
	
	  public Role() {
	        
	    }
	public Role(String nom) {
		super();
		this.nom_role=nom;
		
	}
	
	public int getId_role() {
		return id;
	}

	public String getNom_role() {
		return nom_role;
	}

	public void setNom_role(String nom_role) {
		this.nom_role = nom_role;
	}
	
}
