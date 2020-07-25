<%@page import="appice.nudal.Models.POJO_AlteracaoModalidade"%>
<%  
//    response.setHeader("Cache-Control", "no-cache", "no-store", "must-revalidate");
    
      String username = (String) session.getAttribute("username");
if (username == null)
    {
        response.sendRedirect("login");
    }
%>
<%@page import="java.util.List"%>
<%@page import="appice.nudal.DAO.BDprincipal"%>
<%@page import="appice.nudal.Models.POJO_Login"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="dao" class="appice.nudal.DAO.BDprincipal"/>
<%@page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
         <script language="JavaScript">
                function ConfirmarAlteracao(){		
                    if (confirm ("Atenção! Esse usuário será deletado com esta operação. Deseja continuar?"))		
                        return true;	
                    else		
                        return false;
                }
            </script>
        <link rel="shortcut icon" href="iconnudal.ico"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">
        <link href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Usuários</title>
        <link rel="stylesheet" type="text/css" href="css/exibirusuarios.css">
        <link rel="stylesheet" type="text/css" href="css/animate.css">
         <link rel="stylesheet" type="text/css" href="css/titulos.css">
    </head>
    <body>
<jsp:include page = "sidemenu.jsp" />
     <div class="minhamain">
            <div class="alert alert-success">
  <i class="fa fa-eye fa-lg"></i><strong> Usuários</strong> 
      <a class="btn icon-btn btn-outline-success btn-sm pull-right" href="inserirUsuario">
                    <span class="fa fa-plus"></span>
                    Adicionar Usuario
                </a>
        </div>
           <table>
              <!-- percorre contatos montando as linhas da tabela -->
              <c:forEach var="usuario" items="${dao.getUsuarios()}">
                  <ul class="botao">
                      <dl class="list-group-item"><h3>Email: </h3><h2>${usuario.email}</h2></dl>
                      <dl class="list-group-item"><h3>Tipo: </h3> <h2>${usuario.tipo}</h2></dl>
                      <dl class="list-group-item"><form action="RemoverUsuario" method="post"> <input type="text" value="${usuario.email}" hidden="" name="email" id="email"> <button type="submit" onClick="return ConfirmarAlteracao()" name="comando" id="DeletarUsuario"  value="Deletar Usuario" class="btn btn-outline-danger btn-sm btn-responsive"><i class="fa fa-trash-o"></i> Excluir Usuário</button></form></dl>
                  </ul>
              </c:forEach>
          </table>
        </div>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    
     <script src="bootstrap-notify/bootstrap-notify.min.js"></script>
     
       <%
            String statusdeletar = (String) request.getAttribute("status");
            String statusinserir = (String) request.getAttribute("statusinserir");
            if (statusdeletar != null && statusdeletar.equals("ok")){ %>
      <script>
        $.notify({
      icon: 'fa fa-check',      
      title: '<strong>Sucesso</strong>',
      message: "Usuário deletado!"
    },{
       element: 'body',
      type: 'success text-light bg-success',
      animate: {
        enter: 'animated fadeInUp',
        exit: 'animated fadeOutRight'
      }, placement: {
        from: "top",
        align: "right"
      }
    });
           </script>
           <% } else if(statusdeletar != null && statusdeletar.equals("erro")) { %>
           
             <script>
        $.notify({
      icon: 'fa fa-times-circle',      
      title: '<strong>Erro!</strong>',
      message: "Usuário não deletado!"
    },{
       element: 'body',
      type: 'danger',
      animate: {
        enter: 'animated fadeInUp',
        exit: 'animated fadeOutRight'
      }, placement: {
        from: "top",
        align: "right"
      }
    });
           </script>
           
           <% } %>

<% if(statusinserir != null && statusinserir.equals("ok")){ %>
     <script>
        $.notify({
      icon: 'fa fa-check',      
      title: '<strong>Sucesso</strong>',
      message: "Usuário inserido!"
    },{
       element: 'body',
      type: 'success text-light bg-success',
      animate: {
        enter: 'animated fadeInUp',
        exit: 'animated fadeOutRight'
      }, placement: {
        from: "top",
        align: "right"
      }
    });
           </script>
           <% } else if(statusinserir != null && statusinserir.equals("erro")) { %>
           
            <script>
        $.notify({
      icon: 'fa fa-times-circle',      
      title: '<strong>Erro!</strong>',
      message: "Usuário não inserido! Clique aqui e tente novamente.",
      url: "/inserirUsuario.jsp"
    },{
       element: 'body',
      type: 'danger',
      animate: {
        enter: 'animated fadeInUp',
        exit: 'animated fadeOutRight'
      }, placement: {
        from: "top",
        align: "right"
      }
    });
           </script>
           <%}%>
</html>
