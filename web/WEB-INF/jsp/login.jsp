<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
	<link href='https://fonts.googleapis.com/css?family=Passion+One' rel='stylesheet' type='text/css'>
	<link href='https://fonts.googleapis.com/css?family=Oxygen' rel='stylesheet' type='text/css'>

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
        <link rel="shortcut icon" href="iconnudal.ico"/>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Nudal Login</title>
        <style>
            h1 { 
                font-style: italic; }  
            body {
                background-color: #ffffff; }
                .sombra {
            box-shadow: 0 0 0.6px 0.6px black; /*THIS does not work as expected*/
        }
        @media (max-width: 900px) {
            .imagem { width: 265px; height: 106px } }
        
        @media (max-width: 320px) {
           .title { font-size: 90%; }
        </style>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-success">
  <a class="navbar-brand title" style="font-style: italic; font-family: sans-serif;" href="index.jsp">Painel de Controle Nudal</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNav">
    <ul class="navbar-nav">
       <li class="nav-item">
        <a class="nav-link" href="http://www.uece.br/uece/index.php/noticias/95002-2018-02-02-17-23-38">Saiba mais</a>
      </li>
       <li class="nav-item">
        <a class="nav-link" href="http://www.uece.br">Portal da Uece</a>
       </li>
        <li class="nav-item">
        <a class="nav-link" href="https://www.instagram.com/appice_software/">Appice Software</a>
      </li>
    </ul>
  </div>
</nav>
        <% 
    if(null!=request.getAttribute("errorMessage"))
    {     
     %>
        <div class="alert alert-danger">
  <i class="fa fa-warning fa-lg"></i><strong> Erro!</strong> <%= request.getAttribute("errorMessage") %>
        </div>
    <% } %>
    
        <div class="container col-lg-4">
            <br>
        <img src="nudal.jpg" class="img-fluid rounded mx-auto d-block imagem" alt="Ceará Interativo">
        <br>
        <div class="card bg-light mb-3 rounded sombra">
            <div class="card-header">Login</div>
            
            <div class="card-body">
            <form class="form-group" action="LoginIniciado" method="post">
                <div class="input-group">
                <span class="input-group-text"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                <input class="form-control" placeholder="Insira seu email" type="text" name="email">
                </div><br>
                 <div class="input-group">
                <span class="input-group-text"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
                <input class="form-control" placeholder="Insira sua senha" type="password" name="senha"><br>
                 </div>
                <br><button class="btn btn-success btn-lg btn-block rounded mx-auto d-block" type="submit" value="login">Entrar</button>
            </div>
        </div>
           </form>
        </div>
    </body>
     <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js" integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js" integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm" crossorigin="anonymous"></script>
</html>