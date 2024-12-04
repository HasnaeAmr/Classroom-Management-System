<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
       <%@ page import="metier.entities.*" %>
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
 	List<MatiereFiliereCategorie> list = (List<MatiereFiliereCategorie>) session.getAttribute("mfcs");
    List<Filiere> filieres = (List<Filiere>) session.getAttribute("filieres");
    Integer categorie = (Integer) session.getAttribute("categorie");
    Integer matiere = (Integer) session.getAttribute("matiere");
    Integer fili = (Integer) session.getAttribute("id");
    Integer salle = (Integer) session.getAttribute("salle");
    Integer nbr_heure = (Integer) session.getAttribute("nbr_heure");
    Integer prof = (Integer) session.getAttribute("prof");
    Integer selectedId=(Integer) session.getAttribute("selectedId");
    Map<String, Map<String, String>> emploiDuTemps = (Map<String, Map<String, String>>) session.getAttribute("emploiDuTemps");
    

 %>
 
 <div class="selectfiliere">
    <form action="emploi.em" method="post">
        <label for="filiere">Sélectionnez une filière :</label>
        <select name="Idfiliere" class="choisirfiliere" >
            <option value="">-- Choisissez une filière --</option>
            <% 
                if (filieres != null) {
                    for (Filiere filiere : filieres) { 
            %>
            <option value="<%= filiere.getId_filiere() %>"><%= filiere.getNom_filiere() %></option>
            
            <% 
                    } 
                } 
            %>
        </select>
        <button class="valid-btn">
        <i class='bx bx-navigation icon'></i>
        <input type="hidden" name="action" value="Valider">
        </button>
    </form>

<p>Filiere: 
<% 
    Integer Id = (Integer) session.getAttribute("selectedId");
    if (Id != null && filieres != null) {
        for (Filiere filiere : filieres) {
            if (filiere.getId_filiere() == Id.intValue()) { 
                out.print(filiere.getNom_filiere());
                break;
            }
        }
    } else {
        out.print("Aucune filière sélectionnée");
    }
%>
</p>
</div>
<h2>Emploi du Temps</h2>


<form action="EmploiDuTempsPDFServlet.em" method="post">
<div class="user3">
    <table class="table">
        <!-- Ligne des heures -->
        <tr>
            <th>Jour/Heure</th>
            <% 
                String[] heures = {"8:30 - 10:20", "10:40 - 12:30", "14:30 - 16:20", "16:40 - 18:30"};
                for (String heure : heures) { 
            %>
            <th><%= heure %></th>
            <% } %>
        </tr>
        
        <!-- Colonnes pour les jours -->
        <% 
            String[] jours = {"Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi"};
            for (int j = 0; j < jours.length; j++) { 
        %>
        <tr>
            <th><%= jours[j] %></th>
            <% 
                for (int h = 0; h < heures.length; h++) { 
            %>
            <td>
            
      
        
            <%
                String key = jours[j] + "_" + heures[h];
               
                
                if (emploiDuTemps != null && emploiDuTemps.containsKey(key)) {
                    Map<String, String> details = emploiDuTemps.get(key);
                    out.println( details.get("matiere") + "<br>");
                    out.println( details.get("categorie") + "<br>");
                    out.println( details.get("nbr_heure") +"H"+ "<br>");
                    out.println( details.get("prof") + "<br>");
                    out.println( details.get("salle") + "<br>");
                } else {
                    out.println("");
                }
            
              
             
     
       

         
        %>
        
        <form action="emploi.em" method="post">
            
            <input type="hidden" name="jour" value="<%= jours[j] %>" />
            <input type="hidden" name="heure" value="<%= heures[h] %>" />
            <input type="hidden" name="selectedId" value="<%= request.getAttribute("selectedId") %>">
            
           
            <input type="submit" name="action" value="Remplir" class="emploi">
           
       
            <input type="submit" name="action" value="Reset" class="emploi">

            
        </form>
 
</td>

            <% 
                } 
            %>
        </tr>
        <% }%>
    </table>

    <br/>
    
    <input type="hidden" name="selectedId" value="<%= request.getAttribute("selectedId") %>">
   
    
    <input type="submit" name="action" value="resetable">
   
    
   
    <input type="submit" name="action" value="Telecharger">
  
    
    
</form>
</div>
</body>
</html>
