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
    @JoinColumn(name="id_prof")
    private User prof;
    
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
