
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<title>Gestionnaire de Salles</title>

    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    
    <!----======== CSS&& Bootstrap ======== -->
    <link rel="stylesheet" href="style.css">
    <link rel="stylesheet" type="text/css" href="../css/bootstrap.css">
    
    <!----===== Boxicons CSS ===== -->
    <link href='https://unpkg.com/boxicons@2.1.1/css/boxicons.min.css' rel='stylesheet'>
    
    
</head>
<body>
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
       
        <div class="Dash-text">Salut le gestionnaire <span class="username"><%= user.getNom() %> !</span></div>
        </div>
        <div class="card">
            <div class="C c1"><div class="conti">
            <i class="fa-solid fa-chart-simple"></i></div>
               <div class="contt">
                <h4><%= sallesD.size() %></h4>
                <p>Salles Disponibles</p></div>
            </div>
            <div class="C c2">
            <div class="conti">
            <i class="fa-solid fa-door-closed"></i></div>
            <div class="contt">
            	<h4>
            		<%= sallesV.size() %>
            	</h4>
            	<p>Salles vides</p>
            	</div>
            </div>
            <div class="C c22">
            <div class="conti">
            <i class="fa-solid fa-door-open"></i></div>
            <div class="contt">
            	<h4><%= sallesD.size() - sallesV.size() %></h4>
            	<p>Salles occupées</p>
            	</div>
            </div>
            <div class="C c3">
                <div><% 
                int size = journal != null ? journal.size() : 0;
    
    if (size >= 2) {
%>
        <h3>Les opérations récentes</h3>
        <h5><%= journal.get(size - 1).getDate() %></h5>
        <span><%= journal.get(size - 1).getDescription() %></span>
        <h5><%= journal.get(size - 2).getDate() %></h5>
        <span><%= journal.get(size - 2).getDescription() %></span>
<%
    } else if (size == 1) {
%>
        <h4>Les opérations récentes</h4>
        <h5><%= journal.get(0).getDate() %></h5>
        <span><%= journal.get(0).getDescription() %></span>
<%
    } else {
%>
        <p>Aucune opération récente disponible.</p>
<%
    }
%>
                </div>
                <a href="Journalisation.jsp" class="journalisation" >Show More</a>
            </div>
            <div class="calendar c5 C">
                    <header>
                      <h3></h3>
                      <nav>
                        <button id="prev"></button>
                        <button id="next"></button>
                      </nav>
                    </header>
                    <section>
                      <ul class="days">
                        <li>Sun</li>
                        <li>Mon</li>
                        <li>Tue</li>
                        <li>Wed</li>
                        <li>Thu</li>
                        <li>Fri</li>
                        <li>Sat</li>
                      </ul>
                      <ul class="dates"></ul>
                    </section>
            </div>
           
                
        </div>
       
    </div>

<script src="script.js">
</script>
<%} %>
</body>
</html>
