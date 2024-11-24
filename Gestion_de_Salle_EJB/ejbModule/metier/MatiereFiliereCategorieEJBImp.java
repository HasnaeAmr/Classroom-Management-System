package metier;

import java.util.List;

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

@Stateless(name="MFC")
@Local(MatiereFiliereCategorieLocal.class)
@Remote(MatiereFiliereCategorieRemote.class)
public class MatiereFiliereCategorieEJBImp implements MatiereFiliereCategorieLocal,MatiereFiliereCategorieRemote{
	@PersistenceContext(unitName = "SallesEJB")
	private EntityManager em;
	//Ajout par filiere:
	
	@Override
		public MatiereFiliereCategorie AddMCByFiliere(MatiereFiliereCategorie mfc) {
		em.persist(mfc);
		return mfc;
	}
		//Rechercher une matiere et sa categorie pour une filiere:
	
	@Override

		public MatiereFiliereCategorie getMFC(int id_filiere,int id_matiere,int id_categorie) {
		  try {
		        return em.createQuery("SELECT mfc FROM MatiereFiliereCategorie mfc WHERE mfc.id.id_filiere = :id_filiere AND mfc.id.id_matiere = :id_matiere AND mfc.id.id_categorie = :id_categorie",MatiereFiliereCategorie.class)
		            .setParameter("id_filiere", id_filiere)
		            .setParameter("id_matiere", id_matiere)
		            .setParameter("id_categorie", id_categorie)
		            .getSingleResult();
		    } catch (NoResultException e) {
		        return null; 
		    }
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

		public void setMFCByFiliere(int id_filiere,int id_matiere,int id_categorie,int nbr_heure,int id_prof) {
		 MatiereFiliereCategorie mfc = getMFC(id_filiere, id_matiere, id_categorie);
		 if (mfc != null) {
		       mfc.setNbr_heure(nbr_heure);
		       mfc.setCategorie(em.find(Categorie.class, id_categorie));
		       mfc.setMatiere(em.find(Matiere.class, id_matiere));
		       mfc.setProf(em.find(User.class, id_prof));
		        em.merge(mfc); 
		}
		 else {
			  //test:
		        System.out.println("Not Updated");
		 }
		
	}
	@Override

		public void removeMFCByFiliere(int id_filiere,int id_matiere,int id_categorie) {
		 MatiereFiliereCategorie mfc = getMFC(id_filiere, id_matiere, id_categorie);
		    if (mfc != null) {
		        em.remove(mfc); 
		        //test:
		        System.out.println("deleted");
		    }
	}

}
