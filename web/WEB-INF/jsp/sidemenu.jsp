<%@page import="java.util.List"%>
<%@page import="appice.nudal.DAO.BDprincipal"%>
<%@page import="appice.nudal.Models.POJO_AlteracaoModalidade"%>
<%@page import="appice.nudal.Models.POJO_AlteracaoModalidade"%>
<div class="nav-side-menu">
    <div class="brand">Nudal Mobile</div>
    <i class="fa fa-bars fa-2x toggle-btn" data-toggle="collapse" data-target="#menu-content"></i>
  
        <div class="menu-list">
  
            <ul id="menu-content" class="menu-content collapse out">
                <li>
                  <a href="index">
                  <i class="fa fa-dashboard fa-lg"></i> Painel de Controle
                  </a>
                </li>
            
                 <li>
                  <a href="exibirUsuarios">
                  <i class="fa fa-user fa-lg"></i> Usuários
                  </a>
                  </li>
                  
                  <li>
                  <a href="exibirModalidades">
                  <i class="fa fa-book fa-lg"></i> Modalidades
                  </a>
                </li>

                    <li>
                  <a href="exibirInscricoes">
                  <i class="fa fa-pencil fa-lg"></i> Lista de Inscrições
                  </a>
                </li>
                
                <li>
                        <a href="exibirPedidosAlteracao">
                            <i class="fa fa-exclamation-circle fa-lg"></i> Pedidos de Alteração
                            <%
                                BDprincipal banco = new BDprincipal();
                                List<POJO_AlteracaoModalidade> pedidos = banco.pegarPedidosAbertos();
                                if(!pedidos.isEmpty()){
                                    %>
                                          +<%=pedidos.size()%>
                                    <%
                                }
                            %>
                        </a>
                    </li>
                  
                 <li>
                  <a href="http:///www.uece.br">
                  <i class="fa fa-globe fa-lg"></i> Portal da Uece
                  </a>
                </li>
               
                 <li>
                  <a href="http:///www.instagram.com/appice_software">
                  <i class="fa fa-suitcase fa-lg"></i> Áppice Software
                  </a>
                </li>
                  
                <div class="sair" style="margin-top: 15px; margin-left: 8px;"> <form action="Logout"> <button class="btn btn-light text-danger" type="submit" value="Sair">Sair</button></form></div>

            </ul>
     </div>
</div>