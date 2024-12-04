package metier.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="etat_salle")
public class EtatSalle implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_etat;
	@ManyToOne
    @JoinColumn(name = "id_salle", nullable = false)
    private Salle salle;

    @ManyToOne
    @JoinColumn(name = "id_horaire", nullable = false)
    private Horaire horaire;

    @ManyToOne
    @JoinColumn(name = "id_jour", nullable = false)
    private Jour jour;
    
    @Column(name="etat_salle")
    private boolean etat;
    
    @ManyToOne
    @JoinColumn(name="id_prof", nullable = true)
    private User prof;
    
    public void setSalle(Salle salle) {
    	this.salle=salle;
    }
    public Salle getSalle() {
    	return salle;
    }
    public Horaire getHoraire() {
    	return horaire;
    }
    public Long getIdEtatSalle() {
    	return id_etat;
    }
    public Jour getJour() {
    	return jour;
    }
    public void setHoraire(Horaire horaire) {
    	this.horaire=horaire;
    }
    public void setJour(Jour jour) {
    	this.jour=jour;
    }
    public boolean getEtatSalle() {
    	return etat;
    }
    
    public void setEtatSalle(boolean e) {
    	this.etat=e;
    }
    public void setProf(User prof) {
    	this.prof=prof;
    }
    public User getProf() {
    	return prof;
    }
}
