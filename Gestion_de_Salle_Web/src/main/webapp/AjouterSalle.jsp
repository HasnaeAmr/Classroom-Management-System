<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="metier.entities.Filiere" %>
     <%@ page import="metier.entities.Matiere" %>
     <%@ page import="metier.entities.Categorie" %>
      <%@ page import="metier.entities.User" %>
<%@ page import="java.util.*" %>
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
    
    <title>Ajout de une Salle</title>
</head>
<body><% 
List<Categorie> listCategories = (List<Categorie>) session.getAttribute("categories");
    if (listCategories == null) 
    	listCategories = new ArrayList<Categorie>();
    User user = (User) session.getAttribute("user");
    if (user == null) { 
    	%>
    	    <h1>Vous n'avez pas l'accès à cette page !</h1>
    	    <a href="hh.log">Se connecter</a>
    	<%
    	} else { 
    	%>
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
    	                        <a href="o.gesal">
    	                            <i class='bx bxs-bell icon'></i>
    	                            <span class="text nav-text">Notifications</span>
    	                 </a>
                    </li>
                   
                </ul>
            </div>
        </div>
    </nav>
<div class="home">
        <div class="head"> 
        <div class="notif">
            
            <div>
            <li class="nav-link">
                <a href="LogOut.logout">
                    <i class='bx bx-log-out icon' ></i>
                </a>
            </li>
              </div>
        </div>
                   
        </div>
        
 	<div class="container2" class="ajsalle">
 <form action="mfc.salle" method="post">

        <table>
            
            <tr>
                <th>Nom :</th>
  				<td><input type="text" name="nom" id="nom"  minlength="1" class="modf"></td>
  				</tr>
  				<tr>
  				<th>Capacité :</th>
  				<td>
  				<input type="number" name="capacite" id="capacite"  min="1" class="modf"></td>
                   </tr>
                  
             <tr>
                <th>Type :</th>
                <td>
  <select class="sele" name="type" id="type" required>
    <option  disabled selected>Sélectionnez un type</option>
    <% for (Categorie categorie : listCategories) { %>
      <option value="<%= categorie.getId_categorie() %>">
        <%= categorie.getType_categorie() %>
      </option>
    <% } %>
  </select>
           			
                    </td>
            </tr>
            
         
            <tr><td colspan="2">
                <button class="AjtBtn" type="submit" name="action" value="Ajouter">Ajouter</button>
          </td>
            </tr>
        </table>
   </form>
    </div>
</div>
 <% } %>
</body>
</html>
