<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
        <%@ page import="mediatek2022.Document" %>
        <%@ page import="java.util.ArrayList" %>
        
    
<%

	Object [] data = (Object []) request.getAttribute("data");
	Document [] listDoc = (Document []) data[data.length-1];
	Boolean retourEffectue = (Boolean)request.getAttribute("retourEffectue");

%>


<!DOCTYPE html>
<html>
<head>
<title>Retour d'un document</title>
<meta charset="utf-8">
<link rel="stylesheet" href="./vue-style/style.css">
 <link rel="stylesheet" href="./vue-style/bootstrap.min.css">
 <meta name="viewport" content="width=device-width, initial-scale=1.0">
 <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>

<nav class="navbar">
            <div class="content">
                <div class="logo" style="color: white">JavaEE</div>
                        <ul class=menu-list>
                            <li>
                            	<form action="./retourDocument" method="post">
									<input name="choix" type="hidden" value="emprunt">  
									<button class="boutonnn">    Louer des documents  </button> 
								</form>
                            </li>
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
<h1 style="text-align:center">Information sur vous</h1>
<%
	out.println("<table class='table' style='width:40%;margin: 0 auto;display: block;'><thead><tr><th scope=col>Nom</th><th scope=col>Age</th><th scope=col>Sexe</th><th scope=col>Date de création du compte</th></tr></thead><tbody><tr><th scope=row>"+data[0].toString()+"</th><td>"+data[1].toString()+"</td><td>"+data[2].toString()+"</td><td style='text-align:center'>"+data[3].toString()+"</td></tr></tbody></table>");

%>
<br><br>
<h1 style="text-align: center">Les documents que vous avez emprunté</h1>
<br>
<% 
out.println("<table class='table'><thead><tr><th scope='col'>Id</th><th scope='col'>Titre</th><th scope='col'>Auteur</th><th scope='col'>Description</th><th scope='col'>Date de l'emprunt</th><th scope='col'>Prix</th><th scope='col'></th></tr></thead>");
for(Document d : listDoc){
	String[] tab = d.toString().split("/");
	out.println("<tbody><tr><th scope=row>"+tab[0]+"</th><td>"+tab[1]+"</td><td>"+tab[2]+"</td><td style='width:320px;text-align: justify;'>"+tab[3]+"</td><td>"+tab[4]+"</td><td>"+tab[5]+"</td><td><form  action= \"./retourDocument\" method=\"post\"> <input id=\"docId\" name=\"idDoc\" type=\"hidden\" value=" + d.toString().split("/")[0] +"><input  name=\"choix\" type=\"hidden\" value=\"retour\">  <input  class='bouton'  type=\"submit\" value=\"RETOURNER\"></form></td></tr></tbody>");
}
out.println("</table>");

%>

<script>  
if(<%= retourEffectue %> == null);
if(<%= retourEffectue %> == true)alert("Votre document à bien était rendu");
if(<%= retourEffectue %> == false)alert("Erreur, votre document n'a pu etre retourné, veuillez réessayer");
</script>

</head>
<body>




</body>
</html>