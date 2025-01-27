<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="metier.entities.User" %>    

<%@ page import="java.util.List" %>
<%@ page import="metier.entities.EtatSalle" %> 
<%@ page import="metier.entities.User" %> 
<%@ page import="metier.entities.Categorie" %>
<!DOCTYPE html>
<html >
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <!----======== CSS&& Bootstrap ======== -->
    <link rel="stylesheet" href="profile.css">
    <link rel="stylesheet" type="text/css" href="../css/bootstrap.css">
    
    <!----===== Boxicons CSS ===== -->
    <link href='https://unpkg.com/boxicons@2.1.1/css/boxicons.min.css' rel='stylesheet'>
    <%
User user = (User) session.getAttribute("user");
List<EtatSalle> sallesV = (List<EtatSalle>) session.getAttribute("sallesV");
List<EtatSalle> sallesD = (List<EtatSalle>) session.getAttribute("sallesD");
if (user == null) { 
%>
    <h1>Vous n'avez pas l'accès à cette page !</h1>
    <a href="hh.log">Se connecter</a>
<%
} else { 
%>
    <title>Profile | <%= user.getNom() %> </title>
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
                        <a href="ChefDashBoard.jsp">
                            <i class='bx bxs-dashboard icon'></i>
                            <span class="text nav-text">DashBoard</span>
                        </a>
                    </li>
                    <li class="nav-link" id="CButton">
                        <a href="Profil.jsp">
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
<div class="home">
        <div class="head"> 
            
        <div class="notif">
            
            
            <div><li class="nav-link">
                <a href="hh.logout">
                    <i class='bx bx-log-out icon' ></i>
                    
                </a>
            </li>
            </div>
        </div>
       
       <div class="user2">
    <div class="icon-container">
        <i class='bx bxs-user-circle icon1'></i>
    </div>
    <div class="form-container">
        <form action="Profile.p" method="post">
            <div class="form-group">
                <input type="hidden" name="id" value="<%= user.getId() %>">
                <label for="Nom">Nom :</label>
                <input type="text" name="nom_user" placeholder="Entrez votre nom" value="<%= user.getNom().trim() %>" />
            </div>
            <div class="form-group">
                <label for="Mot_de_passe">Mot de passe :</label>
                <input type="text" name="mot_de_passe" placeholder="Entrez votre mot de passe" value="<%= user.getMdp() %>" />
            </div>
            <div class="btn-container">
                <button type="submit" name="modifier">Modifier le profil</button>
            </div>
        </form>
    </div>
</div>

<!-- <script>
     
        const button = document.getElementById('CButton');

        button.addEventListener('click', () => {
            button.classList.toggle('active');
            document.querySelectorAll('').forEach(btn => {
                if (btn !== button) btn.classList.remove('active');
            });
        });
    
</script> -->

<%} %>
</body>
</html>
