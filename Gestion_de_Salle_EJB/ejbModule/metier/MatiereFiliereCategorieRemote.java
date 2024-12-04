package metier;

import java.util.List;

import javax.ejb.Remote;

import metier.entities.MatiereFiliereCategorie;
@Remote
public interface MatiereFiliereCategorieRemote {
	//Ajout par filiere:
		public MatiereFiliereCategorie AddMCByFiliere(int id_filiere,int id_matiere,int id_categorie,int nbr_heure,int id_prof);
		//Rechercher une matiere et sa categorie pour une filiere
		public MatiereFiliereCategorie getMFC(int id_filiere,int id_matiere,int id_categorie);
		//Rechercher toutes les categories d'une matiere:
		public List<MatiereFiliereCategorie>findCategorie(int id_filiere,int id_matiere);
		//Rechercher toutes les matieres et ses categories pour une filiere:
		public List<MatiereFiliereCategorie> listMFCByFiliere(int id_filiere);
		public void setMFCByFiliere(int id,int id_matiere,int id_categorie,int nbr_heure,int id_prof);
		public void removeMFC(int id);
		MatiereFiliereCategorie getMFC(int id);
		List<MatiereFiliereCategorie> getMFCList(String nom);
		MatiereFiliereCategorie MFCID(int id_filiere, int id_matiere, int id_categorie);
}
