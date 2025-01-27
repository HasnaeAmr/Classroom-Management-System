<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.List" %>
    <%@ page import="java.util.ArrayList" %>
<%@ page import="metier.entities.Salle" %> 
<%@ page import="metier.entities.EtatSalle" %> 
<%@ page import="metier.entities.Jour" %> 
<%@ page import="metier.entities.User" %> 
<%@ page import="metier.entities.Matiere" %> 

<%@ page import="metier.entities.MatiereFiliereCategorie" %> 
<%@ page import="metier.entities.Horaire" %> 
    <%
List<EtatSalle> listSalles = (List<EtatSalle>) session.getAttribute("salles");
    if (listSalles == null) {
    	listSalles = new ArrayList<>();
    }
    List<User> listProfs = (List<User>) session.getAttribute("profs");
    if (listProfs == null) {
    	listProfs = new ArrayList<>();
    }
List<Jour> listJours = (List<Jour>) session.getAttribute("jours");
if (listJours == null) {
	listJours = new ArrayList<>();
}
List<Horaire> listHoraires = (List<Horaire>) session.getAttribute("horaires");
if (listHoraires == null) {
    listHoraires = new ArrayList<>();
}
List<MatiereFiliereCategorie> listMFC = (List<MatiereFiliereCategorie>) session.getAttribute("matieres");
if (listMFC == null) {
	listMFC = new ArrayList<>();
}

List<MatiereFiliereCategorie> listF = (List<MatiereFiliereCategorie>) session.getAttribute("listByF");
if (listF == null) {
	listF = new ArrayList<>();
}

List<MatiereFiliereCategorie> listC = (List<MatiereFiliereCategorie>) session.getAttribute("categoriesDis");
if (listC == null) {
	listC = new ArrayList<>();
}
List<MatiereFiliereCategorie> listDisF = (List<MatiereFiliereCategorie>) session.getAttribute("filieresDis");
if (listDisF == null) {
	listDisF = new ArrayList<>();
}

List<MatiereFiliereCategorie> listMFCF = (List<MatiereFiliereCategorie>) session.getAttribute("matieresF");
if (listMFCF == null) {
	listMFCF = new ArrayList<>();
}
User user = (User) session.getAttribute("user");
if (user == null) { 
%>
    <h1>Vous n'avez pas l'accès à cette page !</h1>
    <a href="hh.log">Se connecter</a>
<%
} else { 
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <!----======== CSS&& Bootstrap ======== -->
    
    <link rel="stylesheet" type="text/css" href="bootstrap.css">
    <link rel="stylesheet" href="salle.css">
    
    <!----===== Boxicons CSS ===== -->
    <link href='https://unpkg.com/boxicons@2.1.1/css/boxicons.min.css' rel='stylesheet'>
    
    
<title>Gestion de Salles</title>
<style>
        .switch {
            position: relative;
            display: inline-block;
            width: 50px;
            height: 24px;
        }

        .switch input {
            opacity: 0;
            width: 0;
            height: 0;
        }

        .slider {
            position: absolute;
            cursor: pointer;
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;
            background-color: #ccc;
            border-radius: 24px;
            transition: background-color 0.4s;
        }

        .slider::before {
            position: absolute;
            content: "";
            height: 18px;
            width: 18px;
            left: 4px;
            bottom: 3px;
            background-color: white;
            border-radius: 50%;
            transition: transform 0.4s;
        }

        input:checked + .slider {
            background-color: #154963;
        }

        input:checked + .slider::before {
            transform: translateX(26px);
        }
    </style>
</head>
<body>
<div class="container">
  <nav class="sidebar clos">
        <header>
            <div class="image-text">
                <span class="image">
                    <img src="test1.png">
                </span>

                <div class="text logo-text">
                    <span class="name">Ensa Agadir</span>
                </div>
            </div>
        </header>
        <div class="menu-bar">
            <div class="menu">
                <ul class="menu-links">
                <%if(user.getRole().getIdRole()==2 ) {%>
                    <li class="nav-link" id="CButton">
                        <a href="DashboardGestionnaire.jsp">
                            <i class='bx bxs-dashboard icon'></i>
                            <span class="text nav-text">DashBoard</span>
                        </a>
                    </li>
                    <li class="nav-link" id="CButton">
                        <a href="Profile.jsp">
                            <i class='bx bx-user-circle icon' ></i>
                            <span class="text nav-text">Profile</span>
                        </a>
                    </li>
					
                    <li class="nav-link" id="CButton">
                        <a href="oo.salle">
                            <i class='bx bxs-group icon'></i>
                            <span class="text nav-text">Salles</span>
                        </a>
                    </li>

                    <li class="nav-link" id="CButton">
                        <a href="o.gesal">
                            <i class='bx bxs-compass icon' ></i>
                            <span class="text nav-text">Gestion de Salles</span>
                        </a>
                    </li>
                   <li class="nav-link" id="CButton">
                        <a href="o.notif">
                            <i class='bx bxs-bell icon'></i>
                            <span class="text nav-text">Notifications</span>
                        </a>
                    </li>
                    <%}else if(user.getRole().getIdRole()==3 ){ %>
                     <li class="nav-link" id="CButton">
                        <a href="j.ournal">
                            <i class='bx bxs-dashboard icon'></i>
                            <span class="text nav-text">DashBoard</span>
                        </a>
                    </li>
                    <li class="nav-link" id="CButton">
                        <a href="Profile.jsp">
                            <i class='bx bx-user-circle icon' ></i>
                            <span class="text nav-text">Profile</span>
                        </a>
                    </li>
					
                     <li class="nav-link" id="CButton">
                        <a href="Reservation.rsv">
                            <i class='bx bxs-chevron-down-circle icon'></i>
                            <span class="text nav-text">Reservation</span>
                        </a>
                    </li>

                    <li class="nav-link" id="CButton">
                        <a href="o.lib">
                            <i class='bx bxs-minus-circle icon' ></i>
                            <span class="text nav-text">Liberation</span>
                        </a>
                    </li>
                    <%}else if(user.getRole().getIdRole()==1){ %>
                    <li class="nav-link" id="CButton">
                        <a href="Filiere.jsp">
                            <i class='bx bxs-compass icon' ></i>
                            <span class="text nav-text">Filières</span>
                        </a>
                    </li>

                    <li class="nav-link" id="CButton">
                        <a href="MFC.jsp">
                            <i class='bx bxs-book icon'></i>
                            <span class="text nav-text">Matiere</span>
                        </a>
                    </li>
                    <li class="nav-link" id="CButton">
                        <a href="EmploiTemps.jsp">
                            <i class='bx bxs-calendar icon'  ></i>
                            <span class="text nav-text">Emploi du Temps</span>
                        </a>
                    </li>
                    <%} %>
                    
                </ul>
            </div>
        </div>
    </nav>
    <div class="search-box">
 <form action="mfc.gesal" method="post">
 <input type="text" name="nom" placeholder="Chercher...">
           <button class="search-btn">
 		<i class='bx bx-search icon'></i>
            <input type="hidden" name="action" value="Chercher">
         </button>
 </form>
</div>

 <div class="t">

  <table class="t1">
    <tr>
      <td>
        <div class="buttns">
        <form action="mfc.gesal" method="post">
          <button class="search-btn ajout-btn aff-btn">
            <i class="bx bx-menu icon"></i>
            <input type="hidden" name="action" value="AfficherTous">
          </button>
          </form>
        </div>
      </td>
      <th class="thf">Filtrage </th>
      <form action="mfc.gesal" method="post">
      <td>
        <select name="horaire" id="horaire">
          <option value="">--Horaire--</option>
          <% for (Horaire horaire : listHoraires) { %>
            <option class="custom-option" value="<%= horaire.getIdHoraire() %>">
              <%= horaire.getNomHoraire() %>
            </option>
          <% } %>
        </select>
      </td>
      
      <td>
        <select name="jour" id="jour">
          <option value="">--Jour--</option>
          <% for (Jour jour : listJours) { %>
            <option  value="<%= jour.getIdJour() %>">
              <%= jour.getNomJour() %>
            </option>
          <% } %>
        </select>
      </td>
      <td>
        <input type="submit" value="Filtrer" name="action" class="bttn">
      </td>
      <td>
        <input type="submit" value="Fin de semaine" name="action" class="bttn" style="width:160px;">
      </td>
      </form>
      
    </tr>
  </table>


 </div>



            <div id="formFiliere">
             <h3>Liste des Salles :</h3>
<div class="user">
    
    <table border="1" id="mfcTable" class="table">
        <tr>
        <th class="nom">Nom</th>
		<th>Jour</th>
		<th class="hor">Horaire</th>
		<th>Filière</th>
		<th>Matière</th>
		<th>Categorie</th>
		<th>Etat</th>
		<th>Changer</th>
        </tr>
        <% 
            if (listSalles != null  && !listSalles.isEmpty()) {
                for (EtatSalle salle : listSalles) { %>
                
                    <tr>
                    <form action="dd.gesal" method="post" id="toselect">
                    <td><%= salle.getSalle().getNomSalle() %></td>
            <td><%= salle.getJour().getNomJour() %></td>
            <td><%= salle.getHoraire().getNomHoraire() %></td>
            
            
            <td>
            <select name="idMFC" id="filiere" required>
                    <% if (salle.getMatiere() != null) { %>
                        <option selected value="<%= salle.getMatiere().getIDmfc() %>">     
                                <%= salle.getMatiere().getFiliere().getNom_filiere() %>
                                 <% } else { %>
                        <option selected disabled>---</option>
                    
                        <% } %>
                        <% for (MatiereFiliereCategorie filiere : listDisF) { %>
                        	<% if (salle.getMatiere() != null && filiere.getFiliere() == salle.getMatiere().getFiliere()){ %>
                        <%continue;} %>
                            <option value="<%= filiere.getIDmfc() %>">
                              <%= filiere.getFiliere().getNom_filiere() %>
                            </option>
                    <% } %>
                </select>
        <button type="submit" name="action" value="FiliereSelected" class="selection">
        <i class='bx bx-navigation icon'></i>
        </button>
             </td>
            
            <td>
            <select name="idMFCM" id="matiere" required>
                    <% if (salle.getMatiere() != null) { %>
                        <option selected value="<%= salle.getMatiere().getIDmfc()%>">    
                                <%= salle.getMatiere().getMatiere().getNom_matiere() %>
                                 <% } else { %>
                        <option selected disabled>---</option>
                    
                        <% } %>
                        <% for (MatiereFiliereCategorie matiere : listF) { %>
                        	<% if (salle.getMatiere() != null && matiere.getMatiere() == salle.getMatiere().getMatiere() ){ %>
                        <%continue;} %>
                            <option value="<%= matiere.getIDmfc() %>">
                               <%= matiere.getMatiere().getNom_matiere() %>
                            </option>
                    <% } %>
                </select>
               
        <button type="submit" name="action" value="MatiereSelected" class="selection">
        <i class='bx bx-navigation icon'></i>
        </button>
        
             </td>
             <td>
            <select name="idMFCT" id="type" required>
                    <% if (salle.getMatiere() != null) { %>
                        <option selected value="<%= salle.getMatiere().getIDmfc() %>">     
                                <%= salle.getMatiere().getCategorie().getType_categorie() %>
                                 <% } else { %>
                        <option selected disabled>---</option>
                    
                        <% } %>
                        <% for (MatiereFiliereCategorie type : listC) { %>
                        
                        	<% if (salle.getMatiere() != null && type.getCategorie() == salle.getMatiere().getCategorie() ){ %>
                        <%continue;} %>
                        
                            <option value="<%= type.getIDmfc() %>">
                               <%= type.getCategorie().getType_categorie() %>
                            </option>
                            
                            
                    <% } %>
                    
                </select>
       <button type="submit" name="action" value="TypeSelected" class="selection">
        <i class='bx bx-navigation icon'></i>
        </button>
             </td>
            <td>
                <label class="switch">
                    <input type="checkbox" name="etat" value="true" <%= salle.getEtatSalle() ? "checked" : "" %> />
                    <span class="slider"></span>
                </label>
            </td>
            <td>
                <input type="hidden" name="idEtat" value="<%= salle.getIdEtatSalle() %>">
                <input type="submit" name="action" value="Valider" class="bttn">
            </td>
            </form>
        </tr>
    
        <% 
                }   
            } else { 
        %>
        <tr>
            <td colspan="6">Aucune information disponible pour votre demande.</td>
        </tr>
        <% } %>
    </table>
    </div>
</div></div>
            <%} %>
<script>
    document.getElementById("filiereBtn").addEventListener("click", function() {
        document.getElementById("toselect").action = "dd.gesal"; // Update form action URL if needed
        document.querySelector("input[name='action']").value = "FiliereSelected"; // Set action to FiliereSelected
        document.getElementById("toselect").submit(); // Submit the form
    });

    document.getElementById("matiereBtn").addEventListener("click", function() {
        document.getElementById("toselect").action = "dd.gesal"; // Update form action URL if needed
        document.querySelector("input[name='action']").value = "MatiereSelected"; // Set action to MatiereSelected
        document.getElementById("toselect").submit(); // Submit the form
    });

    document.getElementById("typeBtn").addEventListener("click", function() {
        document.getElementById("toselect").action = "dd.gesal"; // Update form action URL if needed
        document.querySelector("input[name='action']").value = "TypeSelected"; // Set action to TypeSelected
        document.getElementById("toselect").submit(); // Submit the form
    });
</script>

</body>
</html>
