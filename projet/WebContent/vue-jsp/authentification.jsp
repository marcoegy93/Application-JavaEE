<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%
	String message = (String) request.getAttribute("message");
	if(message==null){
		message="";
	}
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">

<title>identification</title>
<link rel="stylesheet" href="./vue-style/style.css">
 <link rel="stylesheet" href="./vue-style/bootstrap.min.css">

 <meta name="viewport" content="width=device-width, initial-scale=1.0">
 <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
</head>

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
.bouton {
    margin: 0 auto;

    background-color: black;
    border: none;
    color: white;
    padding: 15px 32px;
    text-align: center;
    text-decoration: none;
    display: block;
    font-size: 16px;
  }
</style>

<br><br><br><br><br>



<h1 style="text-align: center">Connexion</h1>

<!-- <form action="./authentification" method="post"> -->
<!-- 	Login : <input type="text" name="login" /> -->
<!-- 	Mot de passe  : <input type="text" name="mdp" />	 -->
<!-- 	<button> Valider </button> -->
<!-- </form> -->
<form class="form" action="./authentification" method="post">
    <label>Login</label>
    <input     name="login"     class="form-control" type="text"><br/>
    <label>Mot de passe</label>
    <input  name="mdp"  class="form-control" type="password"><br/>             
    <input class="bouton" type= "submit"  value="VALIDER">
</form>

<p style="text-align:center; color: red"> <%= message%> </p>


</body>
</html>