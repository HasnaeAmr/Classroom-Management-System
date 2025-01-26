<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="metier.entities.User" %>    
<%@ page import="metier.entities.Jour" %> 
<%@ page import="metier.entities.Salle" %> 
<%@ page import="metier.entities.EtatSalle" %> 
<%@ page import="metier.entities.Horaire" %> 
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
    
    <title>Libération</title>
</head>
<body>
<% List<EtatSalle> salles = (List<EtatSalle>) session.getAttribute("sallesP");
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
                    </li>
                    <li class="nav-link" id="CButton">
                        <a href="Notification.notif">
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

	  <div id="formFiliere">
	  <h2 class="tit2">Libération</h2>
	  
<div class="user">
    
    <table border="1" id="mfcTable" class="table">
	<tr>
		
		<th>Nom</th>
		<th>Horaire</th>
		<th>Jour</th>
		<th>Libération</th>
	</tr>
	<% for (EtatSalle salle : salles) { %>
	<form action="dd.lib" method="post">
	<tr>
	<td><%= salle.getSalle().getNomSalle() %></td>
	
	<td>
	<%= salle.getHoraire().getNomHoraire() %>
	</td>
	
	<td><%= salle.getJour().getNomJour()  %>
	</td>
	
	<td>
	
    <input type="hidden" value="<%= salle.getIdEtatSalle() %>" name="id">
    <input type="submit" value="Definitive" name="action" class="bttn" style="
    width: 90px;  background:  #747474; color: white;">
    <input type="submit" value="Exceptionnelle" name="action" class="bttn" class="b" style="
    width: 130px;">
    
	</td>
</tr>
</form>
	<%} %>
	
	</table>
	   </div>
</div>
	<%
} 
%>
</body>
</html>
