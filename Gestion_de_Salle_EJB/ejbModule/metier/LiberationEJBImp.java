package metier;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import metier.entities.Role;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import javax.annotation.Resource;
import javax.transaction.UserTransaction;

import jakarta.transaction.Status;
import metier.entities.EtatSalle;
import metier.entities.Horaire;
import metier.entities.Jour;
import metier.entities.MatiereFiliereCategorie;
import metier.entities.Reservation;
import metier.entities.Salle;
import metier.entities.User;
@Stateless
@Local(LiberationLocal.class)
@Remote(LiberationRemote.class)
public class LiberationEJBImp implements LiberationLocal, LiberationRemote{
	@EJB
    private JournalisationLocal metier;
	@EJB 
	private NotificationLocal metierN;
	@PersistenceContext(unitName = "SallesEJB")
    private EntityManager em;
	@EJB
	private ReservationLocal metierR;
	@Override
	public void libererartionDefinitive(EtatSalle salle, int i) {
		
		if(i==1) {
			System.out.println("dkhelt l lib def auto");
		Query q = em.createQuery("UPDATE EtatSalle es SET es.etat=:e , es.prof=:p WHERE es.id_etat = :id");
		q.setParameter("e", false);
		q.setParameter("p", null);
		q.setParameter("id", salle.getIdEtatSalle());
		q.executeUpdate();

		//notifer le prof
		metierN.notifier(salle.getMatiere().getProf(),"Libération Définitive Automatique de la salle : " +
			salle.getSalle().getNomSalle() +
			" le " + salle.getJour().getNomJour() +
			" à l'horaire "+ salle.getHoraire().getNomHoraire() );
		//notifier les gestio
		Role r = em.find(Role.class,2);
		q = em.createQuery("SELECT u FROM User u WHERE u.role = :r");
		q.setParameter("r",r);
		List<User> users = q.getResultList();
		for(User user : users) {
			metierN.notifier(user, "<b>Libération Définitive</b> de la salle <b>" +
					salle.getSalle().getNomSalle() +
					"</b> le <b>" + salle.getJour().getNomJour() +
					"</b> à l'horaire <b>"+ salle.getHoraire().getNomHoraire()
					+ "</b> par le prof <b>" + salle.getMatiere().getProf().getNom()
					+"</b>");}
		metier.journaliser("<b>Libération définitive Automatique<b> de la salle <b>"+salle.getSalle().getNomSalle() +"</b> par : "+ salle.getMatiere().getProf().getNom() + " pour le <b>"+ salle.getJour().getNomJour() + "</b> dans l'horaire <b>"+ salle.getHoraire().getNomHoraire()+"</b>");
		
		}
		
		else {
			Query q = em.createQuery("UPDATE EtatSalle es SET es.etat=:e , es.matiere=:m WHERE es.id_etat = :id");
			q.setParameter("e", false);
			q.setParameter("m", null);
			q.setParameter("id", salle.getIdEtatSalle());
			q.executeUpdate();

			
			 q = em.createQuery("SELECT u FROM User u WHERE u.role = :r");

			Role r = em.find(Role.class,2);
			q.setParameter("r",r);
			System.out.println("role " + r.getNomRole());
			List<User> users = q.getResultList();
			for(User user : users) {

				System.out.println("user " + user.getNom() );
				metierN.notifier(user, "Libération Définitive de la salle : " +
						salle.getSalle().getNomSalle() +
						" le " + salle.getJour().getNomJour() +
						" à l'horaire "+ salle.getHoraire().getNomHoraire()
						+ " par le prof " + salle.getMatiere().getProf().getNom()
						);
		}
			metierN.notifier(salle.getMatiere().getProf(),"<b>Libération Définitive</b> de la salle <b>" +
					salle.getSalle().getNomSalle() +
					"</b> le <b>" + salle.getJour().getNomJour() +
					"</b> à l'horaire <b>"+ salle.getHoraire().getNomHoraire()+"</b>");

			}
		metierR.RendreApproved(salle.getSalle().getIdSalle(), salle.getHoraire().getIdHoraire(), salle.getJour().getIdJour());
		
	}
	@EJB
	private ReservationLocal rsv;
	@PersistenceContext(unitName = "SallesEJB1")
	private EntityManager em1;
	@EJB
	private SalleLocal metierS;
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public synchronized void libererationExp(EtatSalle salle, int duree) {
		System.out.println("............ LiberationExp called at " );
	    if (salle == null || duree <= 0) {
	        throw new IllegalArgumentException("Salle invalide ou durée incorrecte.");
	    }

	    EtatSalle es = em.find(EtatSalle.class, salle.getIdEtatSalle());
	    if (es == null) {
	        throw new IllegalArgumentException("Salle spécifiée introuvable.");
	    }
	    es.setEtatSalle(false);
	    MatiereFiliereCategorie ancienneMatiere = es.getMatiere();
	    es.setMatiere(null);
	    em.merge(es);  
	    Query q = em.createQuery("select u from User u where u.role=:r"); Role r =
	  		  em.find(Role.class, 2); q.setParameter("r", r); List<User> users =
	  		  q.getResultList(); for (User user : users) { metierN.notifier(user,
	  		  "<b>Libération Exceptionnelle</b> de la salle <b>" + salle.getSalle().getNomSalle() +
	  		  "<b> le <br>" + salle.getJour().getNomJour() + "</b> à l'horaire <b>" +
	  		  salle.getHoraire().getNomHoraire() + "</b> par le prof " +
	  		  salle.getMatiere().getProf().getNom() + " pour <b>" + duree + "</b> semaines" ); }
	  		  metier.journaliser("<b>Libération exceptionnelle</b> de la salle <b>" +
	  		  salle.getSalle().getNomSalle() + "</b> pour le <b>" + salle.getSalle().getNomSalle()
	  		  + "</b> à l'horaire " + salle.getHoraire().getNomHoraire() + " par le prof " +
	  		  salle.getMatiere().getProf().getNom() + " pour <b>" + duree + "</b> semaines )");
	  		synchronized (this) {
	  		  rsv.RendreEncours(salle.getSalle().getIdSalle(), salle.getHoraire().getIdHoraire(), salle.getJour().getIdJour(), duree);
	  		   
	  		}
	  	 ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	    scheduler.schedule(() -> {
	    	 EntityManager emInThread = ((EntityManager) em1.getDelegate()).unwrap(EntityManager.class); 
	    	   	        try {
	            EtatSalle updatedEs = emInThread.find(EtatSalle.class, salle.getIdEtatSalle());
	            if (updatedEs != null) {
	            	
	                metierS.setEtat(salle.getIdEtatSalle(), true, ancienneMatiere);
	                metierN.notifier(salle.getMatiere().getProf(), "La salle <b>"+salle.getSalle().getNomSalle()+"</b> que vous avez libéré depuis "+duree + " semaines, est maintenant <b>diponible pour vous</b> ( Le "+salle.getJour().getNomJour()+" à "+salle.getHoraire().getNomHoraire()+" )");
	                metier.journaliser("<b>Après une libération exceptionnelle</b> pendant "+duree+" semaines, la salle <b>"+salle.getSalle().getNomSalle()+"</b> est <b>réoccupée</b> par "+salle.getMatiere().getProf().getNom()+ "( Le "+salle.getJour().getNomJour() +" à "+salle.getHoraire().getNomHoraire()+")");
	                Query qq = emInThread.createQuery("SELECT u FROM User u WHERE u.role = :r");

	    			Role rr = emInThread.find(Role.class,2);
	    			qq.setParameter("r",rr);
	    			List<User> userss = q.getResultList();
	    			for(User user : userss) {
	    				metierN.notifier(user,"<b>Après une libération exceptionnelle</b> pendant "+duree+" semaines, la salle <b>" +salle.getSalle().getNomSalle()+"</b> est maintenant <b>réoccupée</b> par "+salle.getMatiere().getProf().getNom()+ " ( Le "+salle.getJour().getNomJour() +" à "+salle.getHoraire().getNomHoraire()+" )");
	    			}
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	        	 if (emInThread.isOpen()) {
	                 emInThread.close();  
	             }  
	        }
	    }, duree, TimeUnit.SECONDS);
	    scheduler.shutdown();
		  
		 
		 }
}
