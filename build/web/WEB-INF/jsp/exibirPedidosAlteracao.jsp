<%-- 
    Document   : exibirmModalidades
    Created on : 04/05/2018, 14:26:36
    Author     : Usuario
--%>
<%@page import="java.util.Base64"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.File"%>
<%@page import="appice.nudal.Models.POJO_AlteracaoModalidade"%>
<%
//    response.setHeader("Cache-Control", "no-cache", "no-store", "must-revalidate");

    String username = (String) session.getAttribute("username");
    if (username == null) {
        response.sendRedirect("login");
    }
%>
<%@page import="appice.nudal.DAO.BDprincipal"%>
<%@page import="java.util.List"%>
<%@page import="appice.nudal.Models.POJO_Modalidade"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <link rel="shortcut icon" href="iconnudal.ico"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">
        <link href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Alterações</title>
        <link rel="stylesheet" type="text/css" href="css/exibirpedidos.css">
        <link rel="stylesheet" type="text/css" href="css/animate.css">
    </head>
    <body>
     <jsp:include page = "sidemenu.jsp" />
        <div class="minhamain">
            <div class="alert alert-success">
                <i class="fa fa-eye fa-lg"></i><strong> Lista de pedidos de Alteração</strong>
            </div>
            <%
                 BDprincipal banco = new BDprincipal();
                 List<POJO_AlteracaoModalidade> pedidos = banco.pegarPedidosAbertos();
                if (pedidos.isEmpty()) {
                    out.println("<h3>Não há pedidos de alteração abertos!</h3>");
                } else {
                    out.println("<UL>");
                    for (int i = 0; i < pedidos.size(); i++) {
                    %>
                       
                        <div class="card">
                            <br>
                            <h3 class="titulo"> Nova Imagem:</h3><br><br>
                            <div class="divEsquerda">                                
                                 <img class="titulo rounded img-fluid" alt="img" src="data:image/jpeg;base64,<%= pedidos.get(i).foto64 %>"/>
                            </div>
                            <div class="divDireita">
                                <h4><b><%=pedidos.get(i).nome%></b></h4> 
                                Nova descrição:<p> <%=pedidos.get(i).informacoes%></p><br>
                                Novo Horário:<p> <%=pedidos.get(i).horario%></p><br>
                                <div>
                                    <form method="post" action="aceitarpedido">
                                    <button type="submit" class="btn btn-success btn-block rounded mx-auto d-block aceitar" style="margin-bottom: 10px; margin-top: 10px;" name="id" id="id" value="<%=pedidos.get(i).id%>">Aceitar Pedido</buttton>
                                    </form>
                                     <form method="post" action="negarpedido">
                                    <button type="submit" class="btn btn-success btn-block rounded mx-auto d-block negar" name="id" id="id" value="<%=pedidos.get(i).id%>">Negar Pedido</buttton>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <br>
                    
                    <%
                    }
                    out.println("</UL>");
                }
            %>
        </div>
    </body>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
     <script src="bootstrap-notify/bootstrap-notify.min.js"></script>
 <script>
  $(function(){
  $(".aceitar").on("click",function(){
        $.notify({
      icon: 'fa fa-check',      
      title: '<strong>Você aceitou o pedido!</strong>',
      message:'Modalidade alterada!'
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
   });
});
           </script>
           
            <script>
  $(function(){
  $(".negar").on("click",function(){
        $.notify({
      icon: 'fa fa-exclamation-circle',      
      title: '<strong>Você negou o pedido!</strong>',
      message:'Nenhuma alteração foi feita!'
    },{
       element: 'body',
      type: 'warning',
      animate: {
        enter: 'animated fadeInUp',
        exit: 'animated fadeOutRight'
      }, placement: {
        from: "top",
        align: "right"
      }
    });
   });
});
           </script>
</html>
