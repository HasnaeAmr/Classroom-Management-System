package metier;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import metier.entities.EtatSalle;
import metier.entities.Horaire;
import metier.entities.Jour;
import metier.entities.Salle;
import metier.entities.User;

public class LiberationEJBImp implements LiberationLocal, LiberationRemote{
	@EJB
    private JournalisationLocal metier;
	@PersistenceContext(unitName = "SallesEJB")
    private EntityManager em;
	public void libererartionDefinitive(Salle salle, Horaire horaire, Jour jour, User prof) {
		Query q = em.createQuery("SELECT es FROM EtatSalle es WHERE es.salle = :salle and es.horaire= :horaire and es.jour=:jour");
		q.setParameter("salle", salle);
		q.setParameter("horaire", horaire);
		q.setParameter("jour", jour);
		EtatSalle es = (EtatSalle) q.getSingleResult();
		es.setEtatSalle(false);
		es.setProf(null); 
		metier.journaliser("Liberation définitive de la salle : "+salle.getNomSalle() +" par : "+ prof.getNom() + " pour le "+ jour.getNomJour() + "dans l'horaire "+ horaire.getNomHoraire());
	}
	@Override
	public void libererationExp(Salle salle, Horaire horaire, Jour jour, User prof, int duree) {
		// TODO Auto-generated method stub
		Query q = em.createQuery("SELECT es FROM EtatSalle es WHERE es.salle = :salle and es.horaire= :horaire and es.jour=:jour");
		q.setParameter("salle", salle);
		q.setParameter("horaire", horaire);
		q.setParameter("jour", jour);
		EtatSalle es = (EtatSalle) q.getSingleResult();
		es.setEtatSalle(false);
		es.setProf(null); 
		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	    scheduler.schedule(() -> {
	        es.setEtatSalle(true);
	        es.setProf(prof);
	        em.merge(es);
	        scheduler.shutdown();
	    }, duree, TimeUnit.DAYS); 

		metier.journaliser("Liberation exeptionnelle de " +duree+ " jours pour la salle : "+salle.getNomSalle()+ " pour le "+ jour.getNomJour() + "dans l'horaire "+ horaire.getNomHoraire() +" par : "+ prof.getNom());
	}
	
	}
	
