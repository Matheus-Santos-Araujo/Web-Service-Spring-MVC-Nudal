<%@page import="appice.nudal.Models.POJO_AlteracaoModalidade"%>
<%@page import="java.util.List"%>
<%@page import="appice.nudal.DAO.BDprincipal"%>
<%
//    response.setHeader("Cache-Control", "no-cache", "no-store", "must-revalidate");

    String username = (String) session.getAttribute("username");
    if (username == null) {
        response.sendRedirect("login");
    }
%>

<!DOCTYPE html>
<html>
    <head>
        <link rel="shortcut icon" href="iconnudal.ico"/>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">
        <title>Painel de Controle Nudal</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

        <link href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="css/style.css">
    </head>
    <body> 
        <jsp:include page = "sidemenu.jsp" />
        <div class="minhamain">      

            <div class="alert alert-success">
                <strong>Seja bem vindo</strong> <%= username%> 
            </div>

            <% 
                BDprincipal banco = new BDprincipal();
                int usuarios = banco.peganumerodeusuarios();
                int modalidades = banco.peganumerodemodalidades();
                int inscricoes = banco.peganumerodeinscricoes();
                int pedidos =  banco.peganumerodepedidos();
            %>
          <div class="row botao">
                    <div class="col-md-3">
                        <div class="card p-30 card-fluid" style="background-color: #f2f3f5;">
                            <div class="media">
                                <div class="media-left meida media-middle" style="margin: auto; margin-top: 10px; margin-left: 10%; margin-right: 20%;">
                                    <span><i class="fa fa-user fa-3x text-info f-s-40 color-primary"></i></span>
                                </div>
                                <div class="media-body media-text-right" style="margin: auto; margin-top:10px;">
                                    <h2 class="text-info"><%=usuarios%></h2>
                                    <p class="m-b-0" style="color: #4f5b69; font-size: 75%;">Usuários</p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="card p-30 card-fluid" style="background-color: #f2f3f5;">
                            <div class="media">
                                <div class="media-left meida media-middle" style="margin: auto; margin-top: 10px; margin-left: 10%; margin-right: 20%;">
                                    <span><i class="fa fa-book fa-3x text-success f-s-40 color-primary"></i></span>
                                </div>
                                <div class="media-body media-text-right" style="margin: auto; margin-top:10px;">
                                    <h2 class="text-sucess"><%=modalidades%></h2>
                                    <p class="m-b-0" style="color: #4f5b69; font-size: 73%;">Modalidades</p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="card p-30" style="background-color: #f2f3f5;">
                            <div class="media">
                                <div class="media-left meida media-middle" style="margin: auto; margin-top: 10px; margin-left: 10%; margin-right: 20%;">
                                    <span><i class="fa fa-pencil fa-3x f-s-40 color-primary" style="color:#fc6180;"></i></span>
                                </div>
                                <div class="media-body media-text-right" style="margin: auto; margin-top:10px;">
                                    <h2 style="color:#fc6180;"><%=inscricoes%></h2>
                                    <p class="m-b-0" style="color: #4f5b69; font-size: 75%;">Inscrições</p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="card p-30" style="background-color: #f2f3f5;">
                            <div class="media">
                                <div class="media-left meida media-middle" style="margin: auto; margin-top: 10px; margin-left: 10%; margin-right: 20%;">
                                    <span><i class="fa fa-exclamation-circle fa-3x text-primary f-s-40 color-primary"></i></span>
                                </div>
                                <div class="media-body media-text-right" style="margin: auto; margin-top:10px;">
                                    <h2 class="text-primary"><%=pedidos%></h2>
                                    <p class="m-b-0" style="color: #4f5b69; font-size: 75%;">Pedidos</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
             
                  <div class="container">                   
                <button class="btn btn-outline-dark"  style="margin-bottom: 20px;" disabled>Modalidades</button>
                <br>
                <div class="row">
                    <form action="inserirModalidade" method="get" >
                        <button type="submit" class="btn btn-sq-lg btn-success botao" id="InserirModalidade" value="Inserir Modalidade"/><i class="fa fa-plus fa-5x"></i></br>Inserir modalidade</button>
                    </form>
                    </br>
                    <form action="exibirModalidades" method="get">
                        <button type="submit" class="btn btn-sq-lg btn-info botao" id="ExibirModalidades" value="Exibir Modalidades"/><i class="fa fa-book fa-5x"></i></br>Exibir Modalidades</button>
                    </form>
                      <form action="exibirPedidosAlteracao" method="get">
                        <button type="submit" class="btn btn-sq-lg btn-primary botao" id="ExibirModalidades"/><i class="fa fa-envelope fa-5x"></i></br>Exibir Pedidos</button>
                    </form>
                </div>
                  </div>
            </br>

           <div class="container">
                <button class="btn btn-outline-dark"  style="margin-bottom: 20px;" disabled>Usuários</button>
                <br>
                <div class="row">
                    <form action="inserirUsuario" method="get">
                        <button type="submit" class="btn btn-sq-lg btn-success botao" id="AdicionarUsuario" value="Adicionar Usuario"/><i class="fa fa-user fa-5x"></i></br>Adicionar usuário</button>
                    </form>
                    </br>
                    <form action="exibirUsuarios" method="get">
                        <button type="submit" class="btn btn-sq-lg btn-info botao" id="ExibirUsuarios" value="Exibir Usuarios"/><i class="fa fa-eye fa-5x"></i></br>Exibir usuários</button>
                    </form>
                </div>
            </div>

                </br>
                 <div class="container">
                 <button class="btn btn-outline-dark"  style="margin-bottom: 20px;" disabled>Inscrições</button>
                <form action="exibirInscricoes" method="get">
                    <button type="submit" class="btn btn-sq-lg btn-info  botaoinscricoes" id="ExibirInscricoes" value="Exibir Inscricoes"/><i class="fa fa-book fa-5x"></i></br>Exibir inscrições</button>
                </form>
                 </div>                 
                 <br>
            </div>
    </body>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</html>