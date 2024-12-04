<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
 <form action="filiere.fs" method="post">
 		<input type="text" name="NomFiliereArechercher" placeholder="Search..." >
 		<button class="search-btn">
 		<i class='bx bx-search icon'></i>
 		<input type="hidden" name="action" value="RechercherFiliere" >
 		</button>  
 </form>
</div>

<div>
 <form action="filiere.fs" method="post">
 <button class="search-btn ajout-btn">
 <i class='bx bxs-add-to-queue icon'></i>
         <input type="hidden" name="action" value="AjouterFiliere">
 </button>
 </form>
 </div>
<%List<Filiere> listFiliere = (List<Filiere>) session.getAttribute("filieres"); 
Integer conteur=0;%>


<div id="formFiliere">
<div class="user">
    <table border="1" id="categoryTable" class="table">
        <tr>
            <th>ID</th>
            <th>Filiere</th>
            <th>Effectif</th>
            <th>Actions</th>
        </tr>
        <% 
            
            if (listFiliere != null && !listFiliere.isEmpty()) {
                for (Filiere filiere : listFiliere) {
                	
                
                	
                	
        %>
        <tr>
            <td><%= filiere.getId_filiere() %></td>
            <td><%= filiere.getNom_filiere() %></td>
            <td><%= filiere.getEffectif() %></td>
             
            <td>
           <div class="action">
         <form action="filiere.fs" method="post">
         <button class="btnModif">
         <i class='bx bxs-edit-alt icon'></i>
         <input  type="hidden" name="codeToUpdate" value="<%= filiere.getId_filiere()%>">
         <input type="hidden" name="action" value="ModifierFiliere">
         </button>
         </form>
         <form action="filiere.fs" method="post">
         <button class="btnDelete">
         <i class='bx bx-x icon'  ></i>
          <input  type="hidden" name="codeToDelete" value="<%= filiere.getId_filiere()%>">
         <input type="hidden" name="action" value="SupprimerFiliere">
         </button>
        
             
             </form>
             </div>
             </td>
             
        </tr>
        
        <% 
                } 
            } else { 
        %>
        <tr>
            <td colspan="4">No filiere available</td>
        </tr>
        <% } %>
    </table>
    </div>
   <%--  <form action="filiere.fs" method="post">
     <button>
     <i class='bx bxs-chevrons-left icon'></i>
    <input type="hidden" name="pagination" value="<%=conteur %>" >
    <input type="hidden" name="action" value="suivant" >
    </button>
    <button>
    <i class='bx bxs-chevrons-right icon'></i>
    <input type="hidden" name="pagination" value="<%=conteur %>" >
    <input type="hidden" name="action" value="precedent" >
    </button>
   
   </form> --%>

</div>
</div>
</div>

</body>
</html>
