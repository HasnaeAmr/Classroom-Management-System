<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="metier.entities.Journalisation" %>
    <%@ page import="metier.entities.User" %>    

    <%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="metier.entities.EtatSalle" %> 
<%@ page import="metier.entities.User" %> 
<%@ page import="metier.entities.Categorie" %>

<%@ page import="metier.entities.Notification" %>
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
List<Notification> notifs = (List<Notification>) session.getAttribute("notifs");
if(notifs==null)
	notifs = new ArrayList<>();
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
                   
                    <%}else if(user.getRole().getIdRole()==3 ){ %>
                     <li class="nav-link" id="CButton">
                        <a href="DashboardProf.jsp">
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
                        <a href="ChefDashBoard.jsp">
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
                    <li class="nav-link" id="CButton">
                        <a href="o.notif">
                            <i class='bx bxs-bell icon'></i>
                            <span class="text nav-text">Notifications</span>
                        </a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    </nav><div class="home">
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
        
       <div class="container mt-4">
    <h2 class="mb-3">Notifications</h2>
    <table class="table">
   
    <%
    if(notifs!=null && !notifs.isEmpty()){
    	for (int i = notifs.size() - 1; i >= 0; i--) {
            Notification notif = notifs.get(i); %>
    <div class="notif">
    <tr>
    <form action="dd.log" method="post">
    	<td ><%= notif.getDescription() %> <span class="date"><%= notif.getDate() %></span> </td>
    	<input type="hidden" name="id" value="<%= notif.getId() %>">
    	
    	<!--  <td><button type="submit" name="action" value="delNotif" class="selectionN"><i class="bx bx-trash"></i></button></td>
    	--></form>
    </tr>
    <%}}else{ %>
    <tr>
            <td colspan="1">Aucune Notification disponible pour l'instant.</td>
        </tr>
        <%} %>
    </table></div></div>
<%} %>
</body>
</html>
