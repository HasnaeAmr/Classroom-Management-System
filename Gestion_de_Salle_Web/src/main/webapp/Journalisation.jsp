<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="metier.entities.Journalisation" %>
    <%@ page import="metier.entities.User" %>    

<%@ page import="java.util.List" %>
<%@ page import="metier.entities.EtatSalle" %> 
<%@ page import="metier.entities.User" %> 
<%@ page import="metier.entities.Categorie" %>

<%@ page import="metier.entities.Journalisation" %>
<!DOCTYPE html>
<html>
<head>
 <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    
    <!----======== CSS&& Bootstrap ======== -->
    <link rel="stylesheet" href="style.css">
    <link rel="stylesheet" type="text/css" href="../css/bootstrap.css">
    
    <!----===== Boxicons CSS ===== -->
    <link href='https://unpkg.com/boxicons@2.1.1/css/boxicons.min.css' rel='stylesheet'>
    
 <%
User user = (User) session.getAttribute("user");
List<EtatSalle> sallesV = (List<EtatSalle>) session.getAttribute("sallesV");
List<EtatSalle> sallesD = (List<EtatSalle>) session.getAttribute("sallesD");
List<Journalisation> journal = (List<Journalisation>) session.getAttribute("journal");
if (user == null) { 
%>
    <h1>Vous n'avez pas l'accès à cette page !</h1>
    <a href="hh.log">Se connecter</a>
<%
} else { 
%>
<meta charset="UTF-8">
<title>Journalisation</title>
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
                <a href="LogOut.logout">
                    <i class='bx bx-log-out icon' ></i>
                </a>
            </li>
            </div>
        </div>
       <div class="container mt-4">
    <h4 class="mb-3">Les opérations récentes</h4>
    <table class="table">
  <thead>
    <tr>
      <th scope="col">Date</th>
      <th scope="col">Description</th>
    </tr>
  </thead>
  <tbody>
    <% 
    if (journal != null && !journal.isEmpty()) {
        for (Journalisation entry : journal) { 
    %>
    <tr>
      <td scope="row"><h5><%= entry.getDate() %></h5></td>
      <td scope="row"><%= entry.getDescription() %></td>
    </tr>
    <% 
        } 
    } else { 
    %>
    <tr>
      <td colspan="3" class="text-center">Aucune opération disponible.</td>
    </tr>
    <% 
    }
    %>
  </tbody>
</table>

</div>
<%} %>
</body>
</html>
