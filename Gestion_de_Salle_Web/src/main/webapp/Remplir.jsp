<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="metier.entities.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <!----======== CSS&& Bootstrap ======== -->
    
    <link rel="stylesheet" type="text/css" href="bootstrap.css">
    <link rel="stylesheet" href="filiere.css">
    
    <!----===== Boxicons CSS ===== -->
    <link href='https://unpkg.com/boxicons@2.1.1/css/boxicons.min.css' rel='stylesheet'>
    
    <title>Filiere</title>
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
                    <li class="nav-link" id="CButton">
                        <a href="ChefDashBoard.jsp">
                            <i class='bx bxs-dashboard icon'></i>
                            <span class="text nav-text">DashBoard</span>
                        </a>
                    </li>
                    <li class="nav-link" id="CButton">
                        <a href="ChefProfil.jsp">
                            <i class='bx bx-user-circle icon' ></i>
                            <span class="text nav-text">Profil</span>
                        </a>
                    </li>

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
                </ul>
            </div>

        </div>

    </nav>
    <div class="home">
     <div class="head"> 
            
        <div class="notif">
            
            <div><li class="nav-link">
                <a href="">
                    <i class='bx bxs-bell icon'></i>
                    
                </a>
            </li>

        </div>
            <div><li class="nav-link">
                <a href="">
                    <i class='bx bx-log-out icon' ></i>
                    
                </a>
            </li>
            </div>
        </div>
  </div>
<%
    // Récupération des données de session
    List<MatiereFiliereCategorie> mfcs = (List<MatiereFiliereCategorie>) session.getAttribute("mfcs");
    MatiereFiliereCategorie selectedmfc = (MatiereFiliereCategorie) request.getAttribute("mfc");
  
    Integer selected = (Integer) session.getAttribute("selectedId");
    List<Categorie> categories = (List<Categorie>) session.getAttribute("categories");
    Integer categorieselected = (Integer) session.getAttribute("categorieselected");
    Integer matiereselected = (Integer) session.getAttribute("matiereselected");
   String jour = (String)  request.getAttribute("jour") ;
    String heure =  (String) request.getAttribute("heure") ;
%>


<p>Jour: <%= request.getAttribute("jour") %></p>
<p>Heure: <%= request.getAttribute("heure") %></p>

<!-- Affichage des erreurs -->
<% if (request.getAttribute("errorMessage") != null) { %>
    <div style="color: red;"><strong><%= request.getAttribute("errorMessage") %></strong></div>
<% } %>

<form action="emploi.em" method="post">
    <label for="nom_matiere">Matière :</label>
    <select name="nom_matiere" id="nom_matiere">
        <option value="">-- Choisissez une matière --</option>
        <% 
        if (mfcs != null) {
            Map<Integer, String> map = new HashMap<>();
            for (MatiereFiliereCategorie mfc : mfcs) {
                if (!map.containsKey(mfc.getMatiere().getId_matiere())) {
                    map.put(mfc.getMatiere().getId_matiere(), mfc.getMatiere().getNom_matiere());
                }
            }
            for (Map.Entry<Integer, String> entry : map.entrySet()) { 
                Integer matiereId = entry.getKey();
                String matiereNom = entry.getValue();
        %>
                <option value="<%= matiereId %>" 
                    <%= matiereselected != null && matiereselected.equals(matiereId) ? "selected" : "" %>>
                    <%= matiereNom %>
                </option>
        <% 
            }
        }
        %>
    </select>

    <label for="nom_categorie">Catégorie :</label>
    <select name="nom_categorie" id="nom_categorie">
        <option value="">-- Choisissez une catégorie --</option>
        <% if (categories != null) { %>
            <% for (Categorie cat : categories) { %>
                <option value="<%= cat.getId_categorie() %>"
                    <%= categorieselected != null && categorieselected.equals(cat.getId_categorie()) ? "selected" : "" %>>
                    <%= cat.getType_categorie() %>
                </option>
            <% } %>
        <% } %>
    </select>
  <input type="hidden" name="jour" value="<%= request.getAttribute("jour") %>">
  <input type="hidden" name="heure" value="<%= request.getAttribute("heure") %>">
    <input type="hidden" name="selectedId" value="<%= selected != null ? selected : "" %>">
    <input type="submit" name="action" value="RechercherMFC">
</form>
<form action="emploi.em" method="post">
<% if (selectedmfc != null) { %>
    <h3>Informations sélectionnées :</h3>
    <p>Matière : 
        <input type="text" name="matiere" value="<%= selectedmfc.getMatiere().getNom_matiere() %>" readonly>
    </p>
    <p>Catégorie : 
        <input type="text" name="categorie" value="<%= selectedmfc.getCategorie().getType_categorie() %>" readonly>
    </p>
    <p>Charge Horaire : 
        <input type="text" name="nbr-heure" value="<%= selectedmfc.getNbr_heure() %>" readonly>
    </p>
    <p>Professeur : 
        <input type="text" name="prof" value="<%= selectedmfc.getProf().getNom() %>" readonly>
    </p>

   <p>La liste des Salles disponibles :(<%= selectedmfc.getCategorie().getType_categorie() %>)</p>
    
       <%
    List<Salle> salles = (List<Salle>) request.getAttribute("salleselected");
    if (salles == null) {
        salles = (List<Salle>) session.getAttribute("salleselected");
    }
%>
<% if (salles != null && !salles.isEmpty()) { %>
    <% for (Salle s : salles) { %>
        <label>
            <input type="radio" name="salleselected" value="<%= s.getIdSalle() %>">
            <%= s.getNomSalle() %> (Capacité: <%= s.getCapacite() %>) 
        </label>
        <br>
    <% } %>
<% } else { %>
    <p>Aucune salle disponible.</p>
<% } %>
<input type="hidden" name="jour" value="<%= request.getAttribute("jour") %>">
<input type="hidden" name="heure" value="<%= request.getAttribute("heure") %>">
    <input type="hidden" name="selectedId" value="<%= selected != null ? selected : "" %>">
    <input type="submit" name="action" value="Confirmer">
</form>
<% }else{ %>
    <p>Aucune matière ou catégorie sélectionnée.</p>
<%  }%>

</body>
</html>
