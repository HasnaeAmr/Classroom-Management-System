package metier.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="utilisateur")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nom;
	private String mdp;
	@ManyToOne
	@JoinColumn(name = "id_role")
	private Role role;
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	public User(int id, String nom, String mdp,Role role) {
		super();
		this.id = id;
		this.nom = nom;
		this.mdp = mdp;
		this.role=role;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	
	 
	 public Role getRole() {return role;}
	 public void setRole(Role role) {this.role = role;}

}
