package metier.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="etat_salle")
public class EtatSalle {
	@ManyToOne
    @JoinColumn(name = "id_salle", nullable = false)
    private Salle salle;

    @ManyToOne
    @JoinColumn(name = "id_hor", nullable = false)
    private Horaire horaire;

    @ManyToOne
    @JoinColumn(name = "id_jour", nullable = false)
    private Jour jour;
    
    @Column(name="etat_salle")
    private boolean etat;
    
    public boolean getEtatSalle() {
    	return etat;
    }
    
    public void setEtatSalle(boolean e) {
    	this.etat=e;
    }
}
