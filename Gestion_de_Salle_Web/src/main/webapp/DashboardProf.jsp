<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="metier.entities.User" %>    

<%@ page import="java.util.List" %>
<%@ page import="metier.entities.EtatSalle" %> 
<%@ page import="metier.entities.User" %> 
<%@ page import="metier.entities.Categorie" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Professeur</title>
</head>
<body>
<%
User user = (User) session.getAttribute("user");
List<EtatSalle> salles = (List<EtatSalle>) session.getAttribute("sallesP");
List<EtatSalle> sallesV = (List<EtatSalle>) session.getAttribute("sallesV");
List<EtatSalle> sallesD = (List<EtatSalle>) session.getAttribute("sallesD");
if (user == null) { 
%>
    <h1>Vous n'avez pas accès à cette page !</h1>
<%
} else { 
%>
    <h1>Salut Prof <%= user.getNom() %> !</h1>  
    <div class="boxes">
    <div class="SallesDispo">
            <h4>Salles Disponibles</h4>
            <p><%= sallesD.size() %></p>
            <p>Pour différents Jours et Horaires</p>  
        </div> 
        <div class="VotreSalles">
            <h4>Salles Réservées par vous</h4>
            <p><%= salles.size() %></p> 
            <p>Pour différents Jours et Horaires</p> 
        </div> 
        <div class="SalleVides">
            <h4>Salles Vides</h4>
            <p><%= sallesV.size() %></p>
            <p>Pour différents Jours et Horaires</p>   
        </div>
        <div class="SalleNonVides">
            <h4>Salles Réservées</h4>
            <p><%= sallesD.size() - sallesV.size() %></p>
            <p>Pour différents Jours et Horaires</p>  
        </div> 
    </div>
    <a href="/">Réservation</a>
    <a href="oo.lib">Libération</a>
<%
} 
%>
</body>
</html>
