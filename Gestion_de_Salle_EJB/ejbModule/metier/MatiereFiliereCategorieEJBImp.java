package metier;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;


import metier.entities.MatiereFiliereCategorie;
import metier.entities.User;
import metier.entities.Categorie;
import metier.entities.Matiere;
import metier.entities.Filiere;

@Stateless(name="MFC")
@Local(MatiereFiliereCategorieLocal.class)
@Remote(MatiereFiliereCategorieRemote.class)
public class MatiereFiliereCategorieEJBImp implements MatiereFiliereCategorieLocal,MatiereFiliereCategorieRemote{
	@PersistenceContext(unitName = "SallesEJB")
	private EntityManager em;
	@EJB
    private JournalisationLocal jr;
	//Ajout par filiere:
	
	@Override
		public MatiereFiliereCategorie AddMCByFiliere(int id_filiere,int id_matiere,int id_categorie,int nbr_heure,int id_prof) {
		Filiere fi=em.find(Filiere.class, id_filiere);
		Matiere mat=em.find(Matiere.class, id_matiere);
		Categorie cat=em.find(Categorie.class, id_categorie);
		User prof=em.find(User.class, id_prof);
		MatiereFiliereCategorie mfc=new MatiereFiliereCategorie(mat,fi,cat,nbr_heure,prof);
		em.persist(mfc);
		 jr.journaliser("Ajout d'une matiere à une filiere: " + 
                 ", Matière: " + mfc.getMatiere().getNom_matiere() + 
                 ", Filière: " + mfc.getFiliere().getNom_filiere() + 
                 ", Catégorie: " + mfc.getCategorie().getType_categorie() + 
                 ", Professeur: " + mfc.getProf().getNom() + 
                 ", Nombre d'heures: " + mfc.getNbr_heure());
		return mfc;
	}
		//Rechercher une matiere et sa categorie pour une filiere:
	
	
	@Override
	public MatiereFiliereCategorie getMFC(int id_filiere, int id_matiere, int id_categorie) {
		// TODO Auto-generated method stub
		return null;
	}
		//Rechercher toutes les categories d'une matiere:
	
	@Override

		public List<MatiereFiliereCategorie>findCategorie(int id_filiere,int id_matiere){
		return em.createQuery("SELECT mfc FROM MatiereFiliereCategorie mfc WHERE mfc.id.id_filiere = :id_filiere AND mfc.id.id_matiere = :id_matiere", MatiereFiliereCategorie.class)
		        .setParameter("id_filiere", id_filiere)
		        .setParameter("id_matiere", id_matiere)
		        .getResultList();
		
	}
	
		//Rechercher toutes les matieres et ses categories pour une filiere:
	
	@Override

		public List<MatiereFiliereCategorie> listMFCByFiliere(int id_filiere){
		return em.createQuery("SELECT mfc FROM MatiereFiliereCategorie mfc WHERE mfc.id.id_filiere = :id_filiere",MatiereFiliereCategorie.class)
		        .setParameter("id_filiere", id_filiere)
		        .getResultList();
	}
	@Override

		public void setMFCByFiliere(int id,int id_matiere,int id_categorie,int nbr_heure,int id_prof) {
		 MatiereFiliereCategorie mfc = getMFC(id);
		 if (mfc != null) {
		       mfc.setNbr_heure(nbr_heure);
		       mfc.setCategorie((Categorie) em.find(Categorie.class, id_categorie));
		       mfc.setMatiere(em.find(Matiere.class, id_matiere));
		       mfc.setProf(em.find(User.class, id_prof));
		        em.merge(mfc); 
		        jr.journaliser("Mise à jour réussie d'une Matiere dans un filiere: ID : " + mfc.getIDmfc() +
	                       ", Nouvelle Matière: " + mfc.getMatiere().getNom_matiere() +
	                       ", Nouvelle Filière: " + mfc.getFiliere().getNom_filiere() +
	                       ", Nouvelle Catégorie: " + mfc.getCategorie().getType_categorie() +
	                       ", Nouveau Professeur: " + mfc.getProf().getNom() +
	                       ", Nouvelle Durée: " + mfc.getNbr_heure());
		}
		 else {
			  //test:
		        System.out.println("Not Updated");
		 }
		
	}
	@Override

		public void removeMFC(int id) {
		 MatiereFiliereCategorie mfc=em.find(MatiereFiliereCategorie.class,id);
		    if (mfc != null) {
		        em.remove(mfc); 
		        jr.journaliser("Suppression d'une matière dans une filiere:  "  + 
	                       ", Matière: " + mfc.getMatiere().getNom_matiere()+ 
	                       ", Filière: " + mfc.getFiliere().getNom_filiere() + 
	                       ", Catégorie: " + mfc.getCategorie().getType_categorie() + 
	                       ", Professeur: " + mfc.getProf().getNom());
		        //test:
		        System.out.println("deleted");
		    }
	}
	@Override

	public MatiereFiliereCategorie getMFC(int id) {
	   try {
	        return em.createQuery(
	                "SELECT mfc FROM MatiereFiliereCategorie mfc WHERE mfc.id = :id", 
	                MatiereFiliereCategorie.class)
	            .setParameter("id", id)
	            .getSingleResult();
	    } catch (NoResultException e) {
	        return null; 
	    } catch (Exception e) {
	        e.printStackTrace(); 
	        throw e; 
	    }
}

	@Override
	public List<MatiereFiliereCategorie> getMFCList(String nom) {
	    return em.createQuery("SELECT mfc FROM MatiereFiliereCategorie mfc " +
	                           "WHERE mfc.matiere.nom_matiere = :nom " +
	                           "OR mfc.categorie.type_categorie = :nom " +
	                           "OR mfc.prof.nom = :nom", MatiereFiliereCategorie.class)
	              .setParameter("nom", nom)  
	              .setParameter("nom", nom) 
	              .setParameter("nom", nom)  
	              .getResultList();
	}
	
	@Override

	public MatiereFiliereCategorie MFCID(int id_filiere, int id_matiere, int id_categorie){
	  try {
	        return em.createQuery("SELECT mfc FROM MatiereFiliereCategorie mfc WHERE mfc.filiere.id = :filiereId AND mfc.matiere.id = :matiereId AND mfc.categorie.id = :categorieId", MatiereFiliereCategorie.class)
	                 .setParameter("filiereId", id_filiere)
	                 .setParameter("matiereId", id_matiere)
	                 .setParameter("categorieId", id_categorie)
	                 .getSingleResult();
	    } catch (NoResultException e) {
	        // Handle the case where no result is found
	        return null;
	    }
	        
	
}




}
