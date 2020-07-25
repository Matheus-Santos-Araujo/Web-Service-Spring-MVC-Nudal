<%@page import="appice.nudal.Models.POJO_Modalidade"%>
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
<%@page import="appice.nudal.Models.POJO_Inscricao"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<jsp:useBean id="dao" class="appice.nudal.DAO.BDprincipal"/>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
          <script language="JavaScript">
                function ConfirmarAlteracao(){		
                    if (confirm ("Atenção! Essa inscrição será deletada com esta operação. Deseja continuar?"))		
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
        <title>Inscrições</title>
        <link rel="stylesheet" type="text/css" href="css/exibirinscricoes.css">
         <link rel="stylesheet" type="text/css" href="css/animate.css">
    </head>
    <body>
<jsp:include page = "sidemenu.jsp" />
        <div class="minhamain">
            <div class="alert alert-success">
                <i class="fa fa-eye fa-lg"></i><strong class="diminuir"> Veja as inscrições de novos alunos</strong>
        </div>
          <%   BDprincipal banco = new BDprincipal();                            
               List<POJO_Inscricao> inscricoes = banco.getInscricoes();
               out.println("<UL>");
                    for (int i = 0; i < inscricoes.size(); i++) { 
                      %>
                       <div class="card">
                            <br>
                            <h3 class="titulo"> Imagem do aluno:</h3><br>
                            <div class="divEsquerda">                                
                                <img class="titulo rounded img-fluid" alt="img" src="data:image/jpeg;base64,<%= inscricoes.get(i).foto64 %>"/>
                            </div>
                            <div class="divDireita">
                                <h4><b> <%=inscricoes.get(i).nome%></b></h4>
                                ID:<p> <%=inscricoes.get(i).id%></p><br>
                                Data de nascimento:<p> <%=inscricoes.get(i).nascimento%></p><br>
                                Email: <p><%=inscricoes.get(i).email%></p><br>
                                Peso: <p><%=inscricoes.get(i).peso%></p><br>
                                Modalidade: <p><%=inscricoes.get(i).modalidade%></p><br>
                                Atividade: <p><%=inscricoes.get(i).atividade%></p><br>
                                Altura: <p><%=inscricoes.get(i).altura%></p><br>
                                Sexo: <p><%=inscricoes.get(i).sexo%></p><br>
                                CPF: <p><%=inscricoes.get(i).cpf%></p><br>
                                Número do RG: <p><%=inscricoes.get(i).rg_numero%></p><br>
                                Expedição: <p><%=inscricoes.get(i).rg_expedicao%></p><br>
                                Expedidor: <p><%=inscricoes.get(i).rg_expedidor%></p><br>
                                Rua: <p><%=inscricoes.get(i).endereco_rua%></p><br>
                                Número: <p><%=inscricoes.get(i).endereco_numero%></p><br>
                                Nome do Pai: <p><%=inscricoes.get(i).nome_pai%></p><br>
                                Nome da Mãe: <p><%=inscricoes.get(i).nome_mae%></p><br>
                                Tipo Sanguineo: <p><%=inscricoes.get(i).tiposanguineo%></p><br>
                                IES: <p><%=inscricoes.get(i).ies%></p><br>
                                Celular: <p><%=inscricoes.get(i).telcelular%></p><br>
                                Fixo: <p><%=inscricoes.get(i).telfixo%></p><br>
                                Nome de emergência: <p><%=inscricoes.get(i).nome_emergencia%></p><br>
                                Telefone de emergência: <p><%=inscricoes.get(i).telefone_emergencia%></p><br>
                                Observações: <p><%=inscricoes.get(i).observacoes%></p><br>
                                <br>
                                <form class="botao" action="RemoverInscricao" method="post"> <input type="text" value="<%=inscricoes.get(i).id%>" hidden="" name="id" id="id"> <button type="submit" onClick="return ConfirmarAlteracao()" class="btn btn-outline-danger btn-sm btn-responsive"><i class="fa fa-trash-o"></i> Excluir inscrição</button></form>                                 
                            </div>
                        </div>
                        <br> 
       <%  } out.println("</UL>"); %>
        </div>
    </body>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
 <script src="bootstrap-notify/bootstrap-notify.min.js"></script>
     
       <%
            String statusdeletar = (String) request.getAttribute("status");
            if (statusdeletar != null && statusdeletar.equals("ok")){ %>
      <script>
        $.notify({
      icon: 'fa fa-check',      
      title: '<strong>Sucesso</strong>',
      message: "Inscrição deletada!"
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
      message: "Inscrição não deletada!"
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
</html>
