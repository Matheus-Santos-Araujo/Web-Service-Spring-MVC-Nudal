<%@page import="java.util.Base64"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.File"%>
<%@page import="appice.nudal.Models.POJO_AlteracaoModalidade"%>
<%  
//    response.setHeader("Cache-Control", "no-cache", "no-store", "must-revalidate");
    
    String username = (String) session.getAttribute("username");
    if (username == null)
    {
        response.sendRedirect("login");
    }
%>
<%@page import="appice.nudal.DAO.BDprincipal"%>
<%@page import="java.util.List"%>
<%@page import="appice.nudal.Models.POJO_Modalidade"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="dao" class="appice.nudal.DAO.BDprincipal"/>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
         <script language="JavaScript">
                function ConfirmarAlteracao(){		
                    if (confirm ("Atenção! Essa modalidade será deletada com esta operação. Deseja continuar?"))		
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
        <title>Modalidades</title>
      <link rel="stylesheet" type="text/css" href="css/exibirmodalidades.css">
        <link rel="stylesheet" type="text/css" href="css/animate.css">
    </head>
    <body>
<jsp:include page = "sidemenu.jsp" />
      <div class="minhamain">
            <div class="alert alert-success">
  <i class="fa fa-eye fa-lg"></i><strong> Modalidades</strong>
      <a class="btn icon-btn btn-outline-success btn-sm pull-right" href="inserirModalidade">
                    <span class="fa fa-plus"></span>
                    Adicionar Modalidade
                </a>
        </div>       
            <c:forEach var="modalidade" items="${dao.modalidades}">                          
            <div class="card">
                            <br>
                            <h3 class="titulo"> Imagem da modalidade:</h3><br>
                            <div class="divEsquerda">                                
                                <img class="titulo rounded img-fluid" alt="img" src="data:image/jpeg;base64,${modalidade.foto64}"/>
                            </div>
                            <div class="divDireita">
                                <h4><b> ${modalidade.nome}</b></h4>
                                ID:<p> ${modalidade.id}</p><br>
                                Tipo:<p> ${modalidade.tipo}</p><br>
                                Descrição: <p>${modalidade.informacoes}</p><br>
                                Horário: <p>${modalidade.horario}</p><br>
                                Professor: <p>${modalidade.professor}</p><br>
                                <form action="RemoverModalidade" class = "pull-right" method="post"> <input type="text" value="${modalidade.id}" hidden="" name="id"> <button type="submit" style="margin-bottom: 10px;" onClick="return ConfirmarAlteracao()" name="comando" id="DeletarModalidade"  value="Deletar Modalidade" class="btn btn-outline-danger btn-sm btn-responsive"><i class="fa fa-trash-o"></i> Excluir modalidade</button></form>
                            </div>
                        </div>
                        <br> 
       </c:forEach>
        </div>
    </body>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    <script src="bootstrap-notify/bootstrap-notify.min.js"></script>
     
      <%     
            String statusdeletar = (String) request.getAttribute("status");
            String statusinserir = (String) response.getHeader("statusinserir");
            if (statusdeletar != null && statusdeletar.equals("ok")){ %>
      <script>
        $.notify({
      icon: 'fa fa-check',      
      title: '<strong>Sucesso</strong>',
      message: "Modalidade deletada!"
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
      message: "Modalidade não deletada!"
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
    
   <% if(statusinserir != null && statusinserir.equals("tudoShow")){ %>
     <script>
        $.notify({
      icon: 'fa fa-check',      
      title: '<strong>Sucesso</strong>',
      message: "Modalidade inserida!"
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
           <% } else if(statusinserir != null && statusinserir.equals("deuRuim")) { %>
           
            <script>
        $.notify({
      icon: 'fa fa-check',      
      title: '<strong>Erro!</strong>',
      message: "Usuário não inserido! Clique aqui e tente novamente.",
      url: "/inserirModalidade.jsp"
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
