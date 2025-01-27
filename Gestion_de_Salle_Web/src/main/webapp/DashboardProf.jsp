<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="metier.entities.Journalisation" %>
<%@ page import="metier.entities.Filiere" %>
<%@ page import="metier.entities.*" %>
<%@ page import="java.util.*" %>

<!DOCTYPE html>
<html >
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <!----======== CSS&& Bootstrap ======== -->
    <link rel="stylesheet" href="style1.css">
    <link rel="stylesheet" type="text/css" href="../css/bootstrap.css">
    
    <!----===== Boxicons CSS ===== -->
    <link href='https://unpkg.com/boxicons@2.1.1/css/boxicons.min.css' rel='stylesheet'>
    
    
</head>
<body>
<%
User user = (User) session.getAttribute("user");
List<Filiere> listFiliere = (List<Filiere>) session.getAttribute("filieres");

List<Journalisation> journal = (List<Journalisation>) session.getAttribute("journal");
List<Matiere> listMatiere = (List<Matiere>) session.getAttribute("matieres");
Integer counter1=0;%>

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
                <a href="Notification.jsp">
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
       
        <div class="Dash-text">Hello!<span class="username"><%= user.getNom() %> !</span></div>
        </div>
        <div class="card">
            <div class="C c1">
            <%

                 if (listFiliere != null && !listFiliere.isEmpty()) {
                     counter1=listFiliere.size();
             
             %>
            <p>Filiere :</p>
            <p><%=counter1 %></p>
            <%    } else { 
             %>
            <p>Aucune filière disponible.</p>
    <% 
        } 
    %>
            </div>
            <div class="C c2">
                   <%

                 if (listMatiere != null && !listMatiere.isEmpty()) {
                     counter1=listMatiere.size();
             
             %>
            <p>Matiere :</p>
            <p><%=counter1 %></p>
            <%    } else { 
             %>
            <p>Aucune matiere disponible.</p>
    <% 
        } 
    %>
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
                <a href="Journalisation.jsp" class="journalisation" >Plus ...</a>
            </div>
            
           
                <div class="calendar c4 C">
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

<script src="script.js" >
</script>

</body>
</html>
