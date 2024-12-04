<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="metier.entities.Filiere" %>
<%@ page import="metier.entities.Matiere" %>
<%@ page import="metier.entities.Categorie" %>
<%@ page import="metier.entities.User" %>
<%@ page import="metier.entities.MatiereFiliereCategorie" %>
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
    List<Filiere> filieres = (List<Filiere>) session.getAttribute("filieres");
    List<Matiere> matieres = (List<Matiere>) session.getAttribute("matieres");
    List<Categorie> categories = (List<Categorie>) session.getAttribute("categories");
    List<User> profs = (List<User>) session.getAttribute("profs");
    MatiereFiliereCategorie mfc = (MatiereFiliereCategorie) request.getAttribute("mfc");
%>
  <div class="container2">
<form action="mfc.do" method="post">
    <table>
       
        <tr>
            <td>Matiere:</td>
            <td>
                <select name="nom_matiere" class="choisir">
                    <% 
                        if (matieres != null) {
                            for (Matiere mat : matieres) {
                                boolean selected = mfc != null && mat.getId_matiere() == mfc.getMatiere().getId_matiere();
                    %>
                    <option value="<%= mat.getId_matiere() %>" <%= selected ? "selected" : "" %>>
                        <%= mat.getNom_matiere() %>
                    </option>
                    <%
                            }
                        }
                    %>
                </select>
            </td>
        </tr>

       
        <tr>
            <td>Catégorie:</td>
            <td>
                <select name="nom_categorie" class="choisir">
                    <% 
                        if (categories != null) {
                            for (Categorie cat : categories) {
                                boolean selected = mfc != null && cat.getId_categorie() == mfc.getCategorie().getId_categorie();
                    %>
                    <option value="<%= cat.getId_categorie() %>" <%= selected ? "selected" : "" %>>
                        <%= cat.getType_categorie() %>
                    </option>
                    <%
                            }
                        }
                    %>
                </select>
            </td>
        </tr>

        
        <tr>
            <td>La Charge Horaire:</td>
            <td>
                <input type="text" name="nbr-heure" value="<%= mfc != null ? mfc.getNbr_heure() : "" %>">
            </td>
        </tr>

        
        <tr>
            <td>Professeur:</td>
            <td>
                <select name="nom" class="choisir">
                    <% 
                        if (profs != null) {
                            for (User prof : profs) {
                                if (prof.getRole().getId_role() == 3) { 
                                    boolean selected = mfc != null && prof.getId() == mfc.getProf().getId();
                    %>
                    <option value="<%= prof.getId() %>" <%= selected ? "selected" : "" %>>
                        <%= prof.getNom() %>
                    </option>
                    <%
                                }
                            }
                        }
                    %>
                </select>
            </td>
        </tr>

        
        <tr>
            <td colspan="2">
                <input type="hidden" name="codeToUpdate" value="<%= request.getAttribute("codeToUpdate")%>">
                <input type="submit" name="action" value="Modifier" class="AjtBtn">
            </td>
        </tr>
    </table>
</form>
</div>
</body>
</html>

             