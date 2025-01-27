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
    <link rel="stylesheet" href="login.css">
    <link rel="stylesheet" type="text/css" href="../css/bootstrap.css">
    
    <!----===== Boxicons CSS ===== -->
    <link href='https://unpkg.com/boxicons@2.1.1/css/boxicons.min.css' rel='stylesheet'>
<title>Connexion</title>
</head>
<body>
	
	<div class="form-container">
	
        <form action="Profile.log" method="post">
        <h2>Connexion</h2>
            <div class="form-group">
                <label for="Nom">Nom d'utilisateur : </label>
                <input type="text" name="nom" placeholder="Entrez votre nom" />
            </div>
            <div class="form-group">
                <label for="Mot_de_passe">Mot de passe :</label>
                <input type="text" name="psswd" placeholder="Entrez votre mot de passe" />
            </div>
            <div class="btn-container">
                <button type="submit" name="action" value="LogIn">Se connecter</button>
            </div>
        </form>
    </div>
</body>
</html>
