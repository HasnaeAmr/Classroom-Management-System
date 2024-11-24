<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="metier.entities.Salle" %> 
<%@ page import="metier.entities.Categorie" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
List<Salle> listSalles = (List<Salle>) session.getAttribute("salles");
    if (listSalles == null) 
    	listSalles = new ArrayList<Salle>();
    List<Categorie> listCategories = (List<Categorie>) session.getAttribute("categories");
    if (listCategories == null) 
    	listCategories = new ArrayList<Categorie>();
 %>
 <h3>Liste des Salles :</h3>
 <form action="controleur.salle" method="post">
  <!-- Nom Field -->
  <label for="nom">Nom :</label>
  <input type="text" name="nom" id="nom" required minlength="2"><br>

  <!-- Capacité Field -->
  <label for="capacite">Capacité :</label>
  <input type="number" name="capacite" id="capacite" required min="1"><br>

  <!-- Type (Categorie) Dropdown -->
  <label for="type">Type :</label>
  <select name="type" id="type" required>
    <option  disabled selected>Sélectionnez un type</option>
    <% for (Categorie categorie : listCategories) { %>
      <option value="<%= categorie.getId_categorie() %>">
        <%= categorie.getType_categorie() %>
      </option>
    <% } %>
  </select><br>

  <!-- Submit Button -->
  <input type="submit" name="action" value="Ajouter">


            <table border="1">
                <tr>
                    <th>ID</th>
                    <th>Nom</th>
                    <th>Capacité</th>
                    <th>Type</th>
                    <th>Actions</th>
                </tr>
                <% for (Salle salle : listSalles) { %>
                <tr>
                    <td><%= salle.getIdSalle() %></td>
                    <td><%= salle.getNomSalle() %></td>
                    <td><%= salle.getCapacite() %></td>
                    <td><%= salle.getType().getType_categorie() %></td>
                    <td>
			<form action="dd.salle" method="post">
                <input type="hidden" name="idSalle" value="<%= salle.getIdSalle() %>">
                <input type="hidden" name="action" value="Supprimer">
                <button type="submit">Modifier</button>
                 <button type="submit">Supprimer</button>
            </form> </td>
                </tr>
                <% } %>
            </table>
            </form>
</body>
</html>