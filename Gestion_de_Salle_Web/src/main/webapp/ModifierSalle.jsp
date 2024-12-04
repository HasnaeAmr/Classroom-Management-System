<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="metier.entities.Salle" %> 
<%@ page import="metier.entities.Categorie" %> 
<%
Salle salle = (Salle) session.getAttribute("salle");
 List<Categorie> listCategories = (List<Categorie>) session.getAttribute("categories");
   %>
<html>
<head>
    <title>Modification d'une salle</title>
</head>
<body>
    <h2>Modifier la Salle</h2>
    
    
    <form action="SalleServlet.salle" method="post">
        <input type="hidden" name="action" value="Modifier">
        <input type="hidden" name="idSalle" value="<%= salle.getIdSalle() %> ">
        
        <label for="nom">Nom:</label>
        <input type="text" name="nomModifie" id="nom" value="<%= salle.getNomSalle() %>" required><br><br>
        
        <label for="capacite">Capacité:</label>
        <input type="number" name="capaciteModifie" id="capacite" value="<%= salle.getCapacite() %>" required><br><br>
        
 <label for="type">Type :</label>
  <select name="type" id="typeModifie" required>
    <option selected value=<%= salle.getType().getId_categorie() %>><%= salle.getType().getType_categorie() %></option>
    <% for (Categorie categorie : listCategories) { %>
      <option value="<%= categorie.getId_categorie() %>">
        <%= categorie.getType_categorie() %>
      </option>
    <% } %>
  </select><br>
        
        <input type="submit" value="Update Salle">
    </form>
</body>
</html>
