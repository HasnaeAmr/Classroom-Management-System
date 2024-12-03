<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="metier.entities.User" %>    

<%@ page import="java.util.List" %>
<%@ page import="metier.entities.EtatSalle" %> 
<%@ page import="metier.entities.User" %> 
<%@ page import="metier.entities.Categorie" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Gestionnaire de Salles</title>
</head>
<body>
<%
User user = (User) session.getAttribute("user");
List<EtatSalle> sallesV = (List<EtatSalle>) session.getAttribute("sallesV");
List<EtatSalle> sallesD = (List<EtatSalle>) session.getAttribute("sallesD");
if (user == null) { 
%>
    <h1>Vous n'avez pas l'accès à cette page !</h1>
<%
} else { 
%>
<h1>Hello Gestio</h1>
 <h1>Salut le gestionnaire <%= user.getNom() %> !</h1>  
    <div class="boxes">
    <div class="SallesDispo">
            <h4>Salles Disponibles</h4>
            <p><%= sallesD.size() %></p>
            <p>Pour différents Jours et Horaires</p>  
        </div> 
        <div class="SalleVides">
            <h4>Salles Vides</h4>
            <p><%= sallesV.size() %></p>
            <p>Pour différents Jours et Horaires</p>   
        </div>
        <div class="SalleNonVides">
            <h4>Salles Occupées</h4>
            <p><%= sallesD.size() - sallesV.size() %></p>
            <p>Pour différents Jours et Horaires</p>  
        </div> 
    </div>
    <a href="o.salle">Gérer les salles</a>
    <a href="oo.gesal">Gérer la disponibilité</a>
<%} %>
</body>
</html>
