<%@page import="appice.nudal.Models.POJO_AlteracaoModalidade"%>
<%@page import="java.util.List"%>
<%@page import="appice.nudal.DAO.BDprincipal"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%  
//    response.setHeader("Cache-Control", "no-cache", "no-store", "must-revalidate");
    
      String username = (String) session.getAttribute("username");
if (username == null)
    {
        response.sendRedirect("login");
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="shortcut icon" href="iconnudal.ico"/>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">
        <link href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Inserir usuário</title>
        <link rel="stylesheet" type="text/css" href="css/style.css">
    </head>
    <body>
      <jsp:include page = "sidemenu.jsp" />
<div class="minhamain">
     
            <div class="alert alert-success">
  <strong> <i class="fa fa-plus fa-lg"></i> Adicione um usuário</strong>  
        </div>
    <div class="container col-lg-6">
    <form action="AdicionarUsuario" method="post">
              <input type="text" placeholder="Email do novo usuário" class="form-control" name="email" id="email"/><BR>
              <input type="text" placeholder="Senha do novo usuário" class="form-control" name="senha" id="senha"/><BR><BR>
            <a>Tipo do novo Usuario:</a> <select name="tipo" id="tipo">
                <option label="Professor" value="PROFESSOR"></option>
                <option label="Administrador" value="ADMINISTRADOR"></option>
            </select><BR><BR>
             <button type="submit" class="btn btn-success btn-block rounded mx-auto d-block" name="comando" id="AdicionarUsuario" value="Adicionar Usuario">Adicionar Usuário</buttton>
        </form>
</div>
</div>
    </body>
     <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js" integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js" integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm" crossorigin="anonymous"></script>
</html>
