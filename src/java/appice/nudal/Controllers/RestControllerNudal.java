package appice.nudal.Controllers;

import appice.nudal.DAO.BDprincipal;
import appice.nudal.Email.EnviarEmail;
import appice.nudal.Models.POJO_Inscricao;
import appice.nudal.Models.POJO_Modalidade;
import appice.nudal.Models.POJO_Topico;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestControllerNudal {
    
      public static String usuarioNaoAutorizado = "loginNegado";
      public static String erroDeFuncionamento = "Erro de Execução";
      BDprincipal banco = new BDprincipal();
    
            @RequestMapping(value = "/requisicaoresposta", method = {RequestMethod.POST, RequestMethod.GET})
            public void RemoteProcedureCall(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            response.setContentType("text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
            try (PrintWriter out = response.getWriter()) {    
            BufferedReader br = request.getReader();
            String linhaLida = br.readLine();
            JSONObject requisicaoJson = null;
            String resposta;
            try {
                requisicaoJson = (JSONObject)new JSONParser().parse(linhaLida);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if(requisicaoJson == null){
                resposta = erro(erroDeFuncionamento,"Pedido nulo").toJSONString();
            }else{
                if(!banco.pedidoJaReebido((String)requisicaoJson.get("requestid"))){
                    banco.inserirRequestId((String)requisicaoJson.get("requestid"));
                    resposta = RequisicaoResposta(requisicaoJson).toJSONString();
                }else{
                    resposta = erro(erroDeFuncionamento,"Pedido ja realizado").toJSONString();
                }              
            }
            System.out.println("------------------------------------------------");
            System.out.println("Requisição: "+ linhaLida);
            System.out.println("Resposta: " + resposta);
            System.out.println("------------------------------------------------");  
            out.println(resposta);
            }
           }

    //MÉTODOS===================================================================
    public JSONObject RequisicaoResposta(JSONObject requisicao){
        String Requisicao = requisicao.get("requisicao").toString();
    //--------------------------------------------------------------------------
    //LOGIN---------------------------
    if(Requisicao.equals("login")){
        JSONObject resposta = sucesso();
        String email = (String) requisicao.get("email");
        String senha = (String) requisicao.get("senha");
        String login = new BDprincipal().efetuarLogin(email, senha);
        if(login.equals("negado")||login.equals("erro")){
            resposta.put("resultado", "loginNegado");
        }else{
            resposta.put("resultado", "loginAceito");
            resposta.put("tipo", login);
        }
        return resposta;
    }
    if(Requisicao.equals("alterar_senha")){
        JSONObject resposta = sucesso();
        String email = (String) requisicao.get("email");
        String senha = (String) requisicao.get("senha");
        String novaSenha = (String) requisicao.get("novaSenha");
        String login = new BDprincipal().efetuarLogin(email, senha);
        System.out.println(login);
        if(!login.equals("negado")){
            banco.alterarSenha(email, novaSenha);
            String senhaDoUsuario = 
                    "Informamos que sua senha foi alterada para: "+novaSenha;
            new EnviarEmail().enviar(email, "Alteração de Senha", senhaDoUsuario);
            resposta = sucesso();
        }else{
            resposta = erro(usuarioNaoAutorizado);
        }
        return resposta;
    }
    if(Requisicao.equals("recuperar_senha")){
        JSONObject resposta = sucesso();
        String emailDestinatario = (String) requisicao.get("email");
        String senhaDoUsuario = "Olá professor!<br> Sua senha é:<br>"+banco.pegaSenha(emailDestinatario);
        new EnviarEmail().enviar(emailDestinatario, "Recuperação de Senha", senhaDoUsuario);
        return resposta;
    }
    //MODALIDADES---------------------
     if(Requisicao.equals("pegar_modalidades")){
        JSONObject resposta = sucesso();
        List<POJO_Modalidade> modalidades = banco.getModalidades();
        if(modalidades == null){
            return erro(erroDeFuncionamento);
        }else{
            JSONArray listaDeModalidades = new JSONArray();
            for(int i = 0; i<modalidades.size();i++){
                listaDeModalidades.add(modalidades.get(i).toJSON());
            }
            resposta.put("lista", listaDeModalidades);
        }
        return resposta;
    }
     if(Requisicao.equals("alterar_modalidade")){
        JSONObject resposta;
        String email = (String) requisicao.get("email");
        String senha = (String) requisicao.get("senha");
        POJO_Modalidade modalidade = new POJO_Modalidade((JSONObject) requisicao.get("modalidade"));
        if(banco.login(email, senha)){
            if(banco.pegarModalidade(modalidade.id).professor.equals(email)){
                if(banco.inserirPedidoAlteracao(modalidade)){
                    resposta = sucesso();
                }else{
                    resposta = erro(erroDeFuncionamento);
                }
            }else{
                resposta = erro(usuarioNaoAutorizado,"Você não é o professor dessa modalidade");
            }
        }else{
            resposta = erro(usuarioNaoAutorizado);
        }
        return resposta;
    }
    //TOPICOS-------------------------
    if(Requisicao.equals("inserir_topico")){
        JSONObject resposta;
        String email = (String) requisicao.get("email");
        String senha = (String) requisicao.get("senha");
        POJO_Topico topico = new POJO_Topico((JSONObject) requisicao.get("topico"));
        if(banco.login(email, senha)){
            if(banco.pegarModalidade(topico.modalidade).professor.equals(email)){
                if(banco.inserirTopico(topico)){
                    resposta = sucesso();
                }else{
                    resposta = erro(erroDeFuncionamento);
                }
            }else{
                resposta = erro(usuarioNaoAutorizado,"Você não é o professor dessa modalidade");
            }
        }else{
            resposta = erro(usuarioNaoAutorizado);
        }
        return resposta;
    }
    if(Requisicao.equals("pegar_topico")){
        JSONObject resposta = sucesso();
        String modalidade = (String) requisicao.get("modalidade");
        List<POJO_Topico> topicos = banco.pegarTopicos(Integer.valueOf(modalidade));
        if(topicos == null){
            resposta = erro(erroDeFuncionamento);
        }else{
            JSONArray listaDeTopicos = new JSONArray();
            for(int i = 0; i<topicos.size();i++){
                listaDeTopicos.add(topicos.get(i).toJSON());
            }
            resposta.put("lista", listaDeTopicos);
        }
        return resposta;
    }
    if(Requisicao.equals("alterar_topico")){
        JSONObject resposta;
        String email = (String) requisicao.get("email");
        String senha = (String) requisicao.get("senha");
        POJO_Topico topico = new POJO_Topico((JSONObject) requisicao.get("topico"));
        if(banco.login(email, senha)){
            if(banco.pegarModalidade(topico.modalidade).professor.equals(email)){
                if(banco.alterarTopico(topico)){
                    resposta = sucesso();
                }else{
                    resposta = erro(erroDeFuncionamento);
                }
            }else{
                resposta = erro(usuarioNaoAutorizado,"Você não é o professor dessa modalidade");
            }
        }else{
            resposta = erro(usuarioNaoAutorizado);
        }
        return resposta;
    }
    if(Requisicao.equals("deletar_topico")){
        JSONObject resposta;
        String email = (String) requisicao.get("email");
        String senha = (String) requisicao.get("senha");
        POJO_Topico topico = new POJO_Topico((JSONObject) requisicao.get("topico"));
        if(banco.login(email, senha)){
            if(banco.pegarModalidade(topico.modalidade).professor.equals(email)){
                if(banco.deletarTopico(topico.id)){
                    resposta = sucesso();
                }else{
                    resposta = erro(erroDeFuncionamento);
                }
            }else{
                resposta = erro(usuarioNaoAutorizado,"Você não é o professor dessa modalidade");
            }
        }else{
            resposta = erro(usuarioNaoAutorizado);
        }
        return resposta;
    }
    //INSCRICAO-----------------------
    if(Requisicao.equals("inserir_inscricao")){
        JSONObject resposta;
        POJO_Inscricao inscricao = new POJO_Inscricao((JSONObject) requisicao.get("inscricao"));
        if(banco.inserirInscricao(inscricao)){
            resposta = sucesso();
        }else{
            resposta = erro(erroDeFuncionamento);
        }
        return resposta;
    }
    if(Requisicao.equals("pegar_inscricoes")){
        JSONObject resposta = sucesso();
        String modalidade = (String) requisicao.get("modalidade");
        List<POJO_Inscricao> inscricoes = banco.pegarInscricoes(Integer.valueOf(modalidade));
        if(inscricoes == null){
            resposta = erro(erroDeFuncionamento);
        }else{
            JSONArray listaDeTopicos = new JSONArray();
            for(int i = 0; i<inscricoes.size();i++){
                listaDeTopicos.add(inscricoes.get(i).toJSON());
            }
            resposta.put("lista", listaDeTopicos);
        }
        return resposta;
    }
     if(Requisicao.equals("deletar_inscricao")){
        JSONObject resposta;
        String email = (String) requisicao.get("email");
        String senha = (String) requisicao.get("senha");
        POJO_Inscricao inscricao = new POJO_Inscricao((JSONObject) requisicao.get("inscricao"));
        if(banco.login(email, senha)){
            if(banco.pegarModalidade(inscricao.modalidade).professor.equals(email)){
                if(banco.deletarInscricao(inscricao.id)){
                    resposta = sucesso();
                }else{
                    resposta = erro(erroDeFuncionamento);
                }
            }else{
                resposta = erro(usuarioNaoAutorizado,"Você não é o professor dessa modalidade");
            }
        }else{
            resposta = erro(usuarioNaoAutorizado);
        }
        return resposta;
    }
      return erro("Requisição Desconhecida");
    }   

    //RESULTADOS----------------------------------------------------------------
    public JSONObject sucesso(){
        JSONObject resposta = new JSONObject();
        resposta.put("resultado", "sucesso");
        return resposta;
    }
    public JSONObject erro(String tipo,String detalhe){
        JSONObject erro = new JSONObject();
        erro.put("resultado", "erro");
        erro.put("tipo", tipo);
        erro.put("detalhe", detalhe);
        return erro;
    }
    public JSONObject erro(String tipo){
        JSONObject erro = new JSONObject();
        erro.put("resultado", "erro");
        erro.put("tipo", tipo);
        erro.put("detalhe","");
        return erro;
    }
}
    
