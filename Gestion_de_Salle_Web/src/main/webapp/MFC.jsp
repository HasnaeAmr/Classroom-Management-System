<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="metier.entities.MatiereFiliereCategorie" %>
<%@ page import="metier.entities.Filiere" %>
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
<div class="search-box">
 <form action="mfc.do" method="post">
 <input type="text" name="NomArechercher" placeholder="Search...">
           <button class="search-btn">
 		<i class='bx bx-search icon'></i>
 			<input  type="hidden" name="selectedId" value="<%= request.getAttribute("selectedId")%>">
            <input type="hidden" name="action" value="Rechercher">
         </button>
 </form>
</div>
<% 
    
    List<MatiereFiliereCategorie> list = (List<MatiereFiliereCategorie>) session.getAttribute("mfcs");
    List<Filiere> filieres = (List<Filiere>) session.getAttribute("filieres");
%>


<div class="selectfiliere">
    <form action="mfc.do" method="post">
        <label for="filiere">Sélectionnez une filière :</label>
        <select name="Idfiliere" class="choisirfiliere" >
            <option value="">-- Choisissez une filière --</option>
            <% 
                if (filieres != null) {
                    for (Filiere filiere : filieres) { 
            %>
            <option value="<%= filiere.getId_filiere() %>"><%= filiere.getNom_filiere() %></option>
            
            <% 
                    } 
                } 
            %>
        </select>
        <button class="valid-btn">
        <i class='bx bx-navigation icon'></i>
        <input type="hidden" name="action" value="Valider">
        </button>
    </form>
   
  <p>Filiere: 
<% 
    Integer Id = (Integer) session.getAttribute("selectedId");
    if (Id != null && filieres != null) {
        for (Filiere filiere : filieres) {
            if (filiere.getId_filiere() == Id.intValue()) { 
                out.print(filiere.getNom_filiere());
                break;
            }
        }
    } else {
        out.print("Aucune filière sélectionnée");
    }
%>
</p>

</div>
 <form action="mfc.do" method="post">
  <button class="search-btn ajout-btn">
 <i class='bx bxs-add-to-queue icon'></i>
  <input  type="hidden" name="selectedId" value="<%= request.getAttribute("selectedId")%>">                
 <input type="hidden" name="action" value="AjouterMFC" >
 </button>
 </form>


<div id="formFiliere">
<div class="user">
    
    <table border="1" id="mfcTable" class="table">
        <tr>
            <th>ID</th>
            <th>Matière</th>
            <th>Catégorie</th>
            <th>Charge Horaire</th>
            <th>Professeur</th>
            <th>Actions</th>
        </tr>
        <% 
            if (list != null) {
                boolean hasData = false;
                for (MatiereFiliereCategorie mfc : list) {
        %>
        <tr>
            <td><%= mfc.getIDmfc() %></td>
            <td><%= mfc.getMatiere().getNom_matiere() %></td>
            <td><%= mfc.getCategorie().getType_categorie() %></td>
            <td><%= mfc.getNbr_heure() %></td>
            <td><%= mfc.getProf().getNom() %></td>
            <td>
                <form action="mfc.do" method="post">
                <button class="btnModif">
                <i class='bx bxs-edit-alt icon'></i>
                    <input type="hidden" name="codeToUpdate" value="<%= mfc.getIDmfc() %>">
                    <input type="hidden" name="action" value="ModifierMFC">
                  </button>
                   </form>
                 <form action="mfc.do" method="post">
                 <button class="btnDelete">
               <i class='bx bx-x icon'  ></i>
                    <input type="hidden" name="codeToDelete" value="<%= mfc.getIDmfc() %>">
                    <input type="hidden" name="action" value="Supprimer">
                    </button>
                </form>
            </td>
        </tr>
        <% 
                    hasData = true;
                }
                if (!hasData) { 
        %>
        <tr>
            <td colspan="6">Aucune information disponible pour cette filière.</td>
        </tr>
        <% 
                }
            } else { 
        %>
        <tr>
            <td colspan="6">Veuillez sélectionner une filière pour afficher ses matières.</td>
        </tr>
        <% } %>
    </table>
    </div>
</div>
</body>
</html>
