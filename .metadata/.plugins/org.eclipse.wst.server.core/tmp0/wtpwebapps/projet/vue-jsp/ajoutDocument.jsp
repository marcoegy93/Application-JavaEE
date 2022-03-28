<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
      <%@ page import="java.util.List" %>
    <%@ page import="mediatek2022.Document" %>
    
<%
	String message = (String) request.getAttribute("message");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Ajoutez un nouveau document</title>
<link rel="stylesheet" href="./vue-style/style.css">
 <link rel="stylesheet" href="./vue-style/bootstrap.min.css">

 <meta name="viewport" content="width=device-width, initial-scale=1.0">
 <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
</head>
<body>
<style>
form {
        margin: 0 auto;
            width: 50%;
}

.form-control {
    border-radius: 0px;
}
</style>
<nav class="navbar">
            <div class="content">
                <div class="logo" style="color: white">JavaEE</div>
                        <ul class=menu-list>
                            <li>
								<form action="./retourDocument" method="post">
									<input name="choix" type="hidden" value="deconnexion">  
									<button class="boutonnn">    Déconnexion  </button> 
								</form>
                            </li>
                          </ul>


                <div class="burger">
                    <span></span>
                </div>
        </nav><br><br><br><br><br>

<h1 style="text-align:center">Ajoutez un nouveau document</h1><br>
<form class="form" action="./ajoutDocument" method="post">
<input name="choix" type="hidden" value="ajout">  
	<label>Titre</label>
	<input type="text" class="form-control" name="titre" /><br/>
	<label>Prix de location</label>
	<input type="text" class="form-control" name="prix" /><br/>
	<label>Auteur</label>
	<input type="text" class="form-control" name="auteur" /><br/>
	<label>Description</label>
	<textarea class="form-control" name="description"/></textarea><br/>	
	<SELECT class="form-control" name="typeDoc" size="1">
	<OPTION>CD
	<OPTION>DVD
	<OPTION>livre
	</SELECT><br>
	<input class="bouton" type= "submit"  value="VALIDER">
</form>


<p> <%= message %></p>


</body>
</html>