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
                <a href="">
                    <i class='bx bx-log-out icon' ></i>
                    
                </a>
            </li>
            </div>
        </div>
       
        <div class="user2">
            <div >
            <i class='bx bxs-user-circle icon1'></i>
            </div >
            <div class="text-iconn">
            <form action="Profile.p" method="post">
                <div class="form-group">
                <input type="hidden" name="id" value="<%= user.getId() %>">
                <label for="Nom">Nom :</label>
                <input type="text" name="nom_user" value=" <%= user.getNom() %> " />
                </div>
            
            
            <div class="form-group">
            <label for="Mot_de_passe">Mot de passe :</label>
            <input type="text" name="mot_de_passe" value="<%= user.getMdp() %>" />
            </div>
            
			 </div>
            </div>
            <div class="btn"><button>
        	<input type="submit" value="Modifier le profile" name="modifier"></button>
        	</div>
        </form>
            
          
       
    
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
