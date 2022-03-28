<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ page import="java.util.List" %>
    <%@ page import="mediatek2022.Document" %>
    <%@ page import="mediatek2022.Mediatheque" %>
    <%@ page import="mediatek2022.Utilisateur" %>
  
    
<%
List<Document> listDoc = (List<Document>) request.getAttribute("listeDoc"); 
Boolean empruntEffectue = (Boolean)request.getAttribute("empruntEffectuer");
%>



<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">

<title>Emprunt d'un document</title>
<link rel="stylesheet" href="./vue-style/style.css">
 <link rel="stylesheet" href="./vue-style/bootstrap.min.css">

 <meta name="viewport" content="width=device-width, initial-scale=1.0">
 <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>


</head>
<body>

<nav class="navbar">
            <div class="content">
                <div class="logo" style="color: white">JavaEE</div>
                        <ul class=menu-list>
                            <li>
                            	<form action="./locationDocument" method="post">
									<input name="choix" type="hidden" value="retour">  
									<button class="boutonnn">    Voir les documents que j'ai loué  </button> 
								</form>
                            </li>
                            <li>
                            	<form action="./locationDocument" method="post">
									<input name="choix" type="hidden" value="deconnexion">  
									<button class="boutonnn">    Déconnexion  </button> 
								</form>
                            </li>
                          </ul>


                <div class="burger">
                    <span></span>
                </div>
        </nav>
        <br><br><br><br>


<br><br>
<h1 style="text-align: center">Bienvenue ! Voici les documents disponibles</h1>
<br><br>



<% 
out.println("<table class='table'><thead><tr><th scope='col'>Id</th><th scope='col'>Titre</th><th scope='col'>Auteur</th><th scope='col'>Description</th><th scope='col'>Prix</th><th scope='col'></th></tr></thead>");
for(Document d : listDoc){
	String[] tab = d.toString().split("/");
	out.println("<tbody><tr><th scope=row>"+tab[0]+"</th><td>"+tab[1]+"</td><td>"+tab[2]+"</td><td style='width: 400px;text-align: justify;'>"+tab[3]+"</td><td>"+tab[4]+"</td><td><form  action= \"./locationDocument\" method=\"post\"> <input id=\"docId\" name=\"idDoc\" type=\"hidden\" value=" + d.toString().split("/")[0] +"><input  name=\"choix\" type=\"hidden\" value=\"emprunt\">  <input  class='bouton' type=\"submit\" value=\"Louer\"></form></td></tr></tbody>");
} 

out.println("</table>");

%>

<script>  
if(<%= empruntEffectue %> == null);
if(<%= empruntEffectue %> == true)alert("Votre document à bien était loué");
if(<%= empruntEffectue %> == false)alert("Erreur, votre document n'a pu etre emprunté car un autre utilisateur la emprunté avant vous");
</script>

</body>
</html>