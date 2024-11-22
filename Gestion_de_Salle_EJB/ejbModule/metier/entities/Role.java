package metier.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Role implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_role;
	@OneToMany(mappedBy = "id_role") 
    private List<User> utilisateurs;
	@Column(name="nom_role")
	String nom_role;

	public Role(String nom) {
		super();
		this.nom_role=nom;
		
	}
	
	public Long getIdRome() {
		return id_role;
	}
	public String getNomRole() {
        return nom_role;
    }

    // Setter method for nom_role
    public void setNomRole(String nom_role) {
        this.nom_role = nom_role;
    }
	
}
